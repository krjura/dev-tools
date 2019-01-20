import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {Base64EncoderComponent} from './pages/base64-encoder/base64-encoder.component';

const routes: Routes = [
  {
    path: 'base64-decoder',
    component: Base64EncoderComponent
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
