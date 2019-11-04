<template>
  <v-layout text-xs-center wrap>
    <v-spacer></v-spacer>
    <v-spacer></v-spacer>
    <v-layout v-if="userStatus" key="login" row>
      <v-flex>
        <span>{{ user.displayName }}</span>
      </v-flex>
      <v-flex>
        <v-btn color="info" @click="doLogout">
          SIGN OUT
        </v-btn>
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

    async doLogin(): Promise<void> {
      const tmpUser = await Firebase.login();
      this.user = tmpUser.user;
      this.userStatus = true;
    }

    doLogout(): void {
      Firebase.logout();
      this.user = {};
      this.userStatus = false;
    }
}
</script>
