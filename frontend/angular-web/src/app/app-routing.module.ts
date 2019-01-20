import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { Base64EncoderComponent } from './pages/base64-encoder/base64-encoder.component';
import { Base64DecoderComponent } from './pages/base64-decoder/base64-decoder.component';

const routes: Routes = [
  {
    path: 'op/base64-encoder',
    component: Base64EncoderComponent
  },
  {
    path: 'op/base64-decoder',
    component: Base64DecoderComponent
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
