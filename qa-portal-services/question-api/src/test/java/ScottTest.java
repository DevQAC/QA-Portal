import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.qa.portal.common.dto.QuestionDto;
import com.qa.portal.common.exception.QaPortalBusinessException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class ScottTest {

    @Test
    public void setOptionsListForQuestion() {
        try {
            InputStream inputStr = this.getClass().getClassLoader().getResourceAsStream("input.json");
            String input = new BufferedReader(new InputStreamReader(inputStr)).readLine();
            System.out.println(input);
            ObjectMapper objectMapper = new ObjectMapper();
            TypeFactory typeFactory = objectMapper.getTypeFactory();
            List<String> strings = (objectMapper.readValue(input, typeFactory.constructCollectionType(List.class, String.class)));
            System.out.println(strings.size());
        }
        catch (Exception e) {
            throw new QaPortalBusinessException("Could not get options for form questions.");
        }
    }


}
