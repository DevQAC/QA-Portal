package com.qa.portal.admin.services;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.ws.rs.NotFoundException;

import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;

import com.qa.portal.common.exception.QaPortalBusinessException;

public class KeyCloakAdminClient {
	// throw new QaPortalBusinessException
	private String serverUrl;
	private String realm;
	private String clientId;
	private String clientSecret;
	private Keycloak keycloak;
	private UserRepresentation user;
	private CredentialRepresentation credential;
	private UsersResource userResource;
	private RealmResource realmResource;
	List<UserRepresentation> userList;

	public KeyCloakAdminClient(String serverUrl, String realm, String clientId, String clientSecret) {
		this.serverUrl = serverUrl;
		this.realm = realm;
		this.clientId = clientId;
		this.clientSecret = clientSecret;
	}

	public KeyCloakAdminClient() {
		this.serverUrl = "http://localhost:8080/auth";
		this.realm = "qa-portal";
		this.clientId = "";
		this.clientSecret = "";
	}

	public Keycloak build() {
		try {
			keycloak = KeycloakBuilder.builder().serverUrl("http://localhost:8080/auth").realm("qa-portal")
					.username("adminclient1@qa.com").password("pass1").clientId("admin-cli")
					.resteasyClient(new ResteasyClientBuilder().build()).build();
			realmResource = keycloak.realm("qa-portal");
			userResource = realmResource.users();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		return keycloak;
	}
	
	private Optional<List<UserRepresentation>> getUserList() {
		return Optional.ofNullable(userResource.list());
	}
	
	private boolean checkUsernameExists(List<UserRepresentation> ul, String username) {
		Optional<String> user = ul.stream()
				.map(u -> u.getUsername())
				.filter(u -> u != null && u.equals(username))
				.findFirst();
		return user.map(u -> true).orElse(false);
	}
	private boolean checkUsernameExists(String username) {
		return getUserList().map(ul -> checkUsernameExists(ul, username)).orElse(false);
	}
	

	private boolean checkEmailExists(List<UserRepresentation> ul, String email) {
		Optional<String> user = ul.stream()
				.map(u -> u.getEmail())
				.filter(u -> u != null && u.equals(email))
				.findFirst();
		return user.map(u -> true).orElse(false);
	}
	private boolean checkEmailExists(String email) {
		return getUserList().map(ul -> checkEmailExists(ul, email)).orElse(false);
	}

	public boolean createUser(String username, String firstname, String lastname, String email) {
		if (username.equals("") | firstname.equals("") | lastname.equals("") | email.equals("")) {
			throw new QaPortalBusinessException("Missing required fields (required fields: username, firstname, lastname, email).");
		}
		if (!checkEmailExists(email)){
			if (!checkUsernameExists(username)) {
				try {
					getUserList();
					credential = new CredentialRepresentation();
					credential.setType(CredentialRepresentation.PASSWORD);
					credential.setValue("password");
					this.user = new UserRepresentation();
					user.setUsername(username);
					user.setFirstName(firstname);
					user.setLastName(lastname);
					user.setEmail(email);
					user.setCredentials(Arrays.asList(credential));
					user.setEnabled(true);
					keycloak.realm("qa-portal").users().create(user);
					return true;
				} catch (Exception e) {
					e.printStackTrace();
					System.out.println(e.getMessage());
					return false;
				}
			} else {
				throw new QaPortalBusinessException("Username already in use.");
			}
		} else {
			throw new QaPortalBusinessException("Email already in use.");
		}
	}

	public void addRoleToUser(String username, String roleName) {
		if (username != "" && roleName != "") {
			getUserList();
			if (checkUsernameExists(username)) {
				Optional<UserRepresentation> r = userList.stream().filter(u -> u.getUsername().equals(username)).findFirst();
				String id = r.get().getId();
				try {
					RoleRepresentation role = realmResource.roles().get(roleName).toRepresentation();
					userResource.get(id).roles().realmLevel().add(Arrays.asList(role));
				} catch (NotFoundException e) {
					throw new QaPortalBusinessException("Role not found.");
				}
			} else {
				throw new QaPortalBusinessException("Username not found.");
			}
		} else {
			if (username.equals("")) {
				throw new QaPortalBusinessException("Username not supplied.");
			} else {
				throw new QaPortalBusinessException("Role not supplied.");
			}
		}
	}

	public void createRole(String role) {
		if (checkRoleExists(role)) {
			RoleRepresentation roleRepresentation = new RoleRepresentation();
	        roleRepresentation.setName(role);
	        keycloak.realm("qa-portal").roles().create(roleRepresentation);
		}
	}
	
	public void createCohort(String role) {
		createRole("cohort_" + role);
	}

	private Optional<List<RoleRepresentation>> getRoleList() {
		return Optional.ofNullable(realmResource.roles().list());
	}
	private boolean checkRoleExists(List<RoleRepresentation> rl, String roleName) {
		Optional<String> role = rl.stream()
				.map(r -> r.getName())
				.filter(r -> r != null && r.equals(roleName))
				.findFirst();
		return role.map(u -> true).orElse(false);
	}
	private boolean checkRoleExists(String username) {
		return getRoleList().map(ul -> checkRoleExists(ul, username)).orElse(false);
	}
	public void test(String role) {
		List<RoleRepresentation> rrl = realmResource.roles().list();
		for (RoleRepresentation r : rrl) {
			System.out.println(r.getName() + " : " + checkRoleExists(role));
		}
	}
}
