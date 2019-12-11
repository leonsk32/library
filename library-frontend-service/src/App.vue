<template>
  <v-app>
    <v-navigation-drawer
      v-model="drawer"
      absolute
      left
      temporary
    >
      <v-list
        nav
        dense
      >
        <v-list-item-group
          v-model="group"
          active-class="deep-purple--text text--accent-1"
        >

          <v-list-item>
            <v-list-item-title
              @click="to('/')"
            >
              <v-icon>mdi-view-dashboard </v-icon>
              ダッシュボード
            </v-list-item-title>
          </v-list-item>

          <v-list-item>
            <v-list-item-title
              @click="to('/search')"
            >
              <v-icon>mdi-magnify</v-icon>
              検索
            </v-list-item-title>
          </v-list-item>

          <v-list-item>
            <v-list-item-title
              @click="to('/books')"
            >
              <v-icon>mdi-library-books </v-icon>
              貸出一覧
            </v-list-item-title>
          </v-list-item>

          <v-list-item>
            <v-list-item-title
              @click="to('/ranking')"
            >
              <v-icon>mdi-medal</v-icon>
              ランキング
            </v-list-item-title>
          </v-list-item>

          <v-list-item>
            <v-list-item-title
              @click="to('/books/inventory')"
            >
              <v-icon>mdi-briefcase</v-icon>
              棚卸
            </v-list-item-title>
          </v-list-item>
        </v-list-item-group>
      </v-list>
    </v-navigation-drawer>

    <v-app-bar app
      @click="to('/')"
    >
      <v-app-bar-nav-icon @click.stop="drawer = !drawer"></v-app-bar-nav-icon>
      <v-toolbar-title class="headline">
        <span>ADC</span>
        <span class="font-weight-light">Library</span>
      </v-toolbar-title>
      <Auth/>
    </v-app-bar>

    <v-content>
      <router-view></router-view>
    </v-content>
  </v-app>
</template>

<script lang="ts">
import { Component, Vue } from 'vue-property-decorator';
import Auth from '@/components/Auth.vue';

@Component(
  { components: { Auth } },
)
export default class App extends Vue {
    drawer: boolean = false;

    group: any = null;

    to(url: string):void {
      if (this.$route.fullPath === url) {
        return;
      }
      this.$router.push(url);
    }
}
</script>
