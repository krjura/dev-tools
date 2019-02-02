import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { Base64EncoderComponent } from './pages/base64-encoder/base64-encoder.component';
import { Base64DecoderComponent } from './pages/base64-decoder/base64-decoder.component';
import { BCryptPasswordComponent } from './pages/bcrypt-password/bcrypt-password.component';
import { PasswordGeneratorComponent } from './pages/password-generator/password-generator.component';
import { UuidGeneratorComponent } from './pages/uuid-generator/uuid-generator.component';
import { PkiKeyGeneratorComponent } from './pages/pki-key-generator/pki-key-generator.component';

const routes: Routes = [
  {
    path: 'op/base64-encoder',
    component: Base64EncoderComponent
  },
  {
    path: 'op/base64-decoder',
    component: Base64DecoderComponent
  },
  {
    path: 'op/bcrypt-password',
    component: BCryptPasswordComponent
  },
  {
    path: 'op/password-generator',
    component: PasswordGeneratorComponent
  },
  {
    path: 'op/uuid-generate',
    component: UuidGeneratorComponent
  },
  {
    path: 'op/pki-key-generator',
    component: PkiKeyGeneratorComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
