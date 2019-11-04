<template>
  <v-layout text-xs-center wrap>
    <v-spacer></v-spacer>
    <v-spacer></v-spacer>
    <v-layout v-if="userStatus" key="login" row>
      <v-flex xs2>
        <v-btn color="info" @click="doLogout">
          SIGN OUT
        </v-btn>
      </v-flex>
      <v-flex xs2>
        <p class="navbar-item">{{ user.displayName }}</p>
      </v-flex>
    </v-layout>
    <v-layout v-else key="logout" row>
      <v-flex justify-left>
        <v-btn color="info" @click="doLogin">
          SIGN IN
        </v-btn>
      </v-flex>
    </v-layout>
  </v-layout>
</template>

<script lang="ts">
import { Component, Vue } from 'vue-property-decorator';
import Firebase from '@/firebase';

@Component
export default class Auth extends Vue {
    user: any = {};

    userStatus: boolean = false;

    doLogin(): void {
      this.user = Firebase.login();
    }

    doLogout(): void {
      this.user = Firebase.logout();
    }
}
</script>
