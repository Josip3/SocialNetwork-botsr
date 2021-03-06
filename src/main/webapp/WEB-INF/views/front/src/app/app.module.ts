import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';


import {AppComponent} from './app.component';
import {AdminComponent} from './admin/admin.component';
import {ContentComponent} from './content/content.component';
import {RegistrationComponent} from './registration/registration.component';
import {UserComponent} from './user/user.component';
import {RouterModule, Routes} from "@angular/router";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {AuthInterceptor} from "../shared/auth-inteceptor";
import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";
import {CanActiveAdmin} from "../shared/can-active/can-active-admin";
import {CanActiveLogin} from "../shared/can-active/can-active-login";
import { FriendListComponent } from './content/friend-list/friend-list.component';
import { MusicComponent } from './content/music/music.component';
import { MyMusicComponent } from './content/my-music/my-music.component';
import { PreviewComponent } from './preview/preview.component';
import { FriendMusicComponent } from './preview/friend-music/friend-music.component';
import { GalleryComponent } from './content/gallery/gallery.component';

const routes: Routes = [
  {path: '', redirectTo: 'sing-in', pathMatch: 'full'},
  {path: 'login', redirectTo: 'sing-in', pathMatch: 'full'},
  {path: 'admin', component: AdminComponent, canActivate: [CanActiveAdmin]},
  {path: 'sing-in', component: RegistrationComponent, canActivate: [CanActiveLogin]},
  {path: 'sign-up', component: RegistrationComponent, canActivate: [CanActiveLogin]},
  {path: 'community', component: ContentComponent, children:[
    {path: 'friends', component: FriendListComponent},
    {path: 'audio', component: MusicComponent},
    {path: 'my-music', component: MyMusicComponent},
  ]},
  {path: 'user/:id', component: UserComponent},
  {path: 'friend/:id', component: PreviewComponent,children:[
    {path: 'friend-music',component: FriendMusicComponent}
  ]},
];


@NgModule({
  declarations: [
    AppComponent,
    AdminComponent,
    ContentComponent,
    RegistrationComponent,
    UserComponent,
    FriendListComponent,
    MusicComponent,
    MyMusicComponent,
    PreviewComponent,
    FriendMusicComponent,
    GalleryComponent,
  ],
  imports: [
    FormsModule,
    HttpClientModule,
    BrowserModule,
    ReactiveFormsModule,
    RouterModule.forRoot(routes, {useHash: true}),
  ],
  providers: [
    CanActiveLogin,
    CanActiveAdmin,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: AuthInterceptor,
      multi: true
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
