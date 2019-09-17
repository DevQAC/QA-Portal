import { enableProdMode } from '@angular/core';
import { platformBrowserDynamic } from '@angular/platform-browser-dynamic';

import { environment } from './environments/environment';
import { QaAdminModule } from './app/qa-admin.module';

if (environment.production) {
  enableProdMode();
}

platformBrowserDynamic().bootstrapModule(QaAdminModule)
  .catch(err => console.error(err));
