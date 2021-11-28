import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';

const routes: Routes = [
  {
    path: 'op/base64',
    loadChildren: () => import('./modules/base64/base64.module').then(m => m.Base64Module)
  },
  {
    path: 'op/generators',
    loadChildren: () => import('./modules/generators/generators.module').then(m => m.GeneratorsModule)
  },
  {
    path: 'op/hr',
    loadChildren: () => import('./modules/country_hr/countryHrModule').then(m => m.CountryHrModule)
  },
  {
    path: 'op/hashing',
    loadChildren: () => import('./modules/hashing/hashing.module').then(m => m.HashingModule)
  },
  {
    path: 'op/json',
    loadChildren: () => import('./modules/json/json.module').then(m => m.JsonModule)
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
