<template>
  <v-container>
    <v-layout
      text-center
      wrap
    >
      <v-flex xs12>
      <v-toolbar flat color="white">
        <v-toolbar-title>読書ランキング(累計)</v-toolbar-title>
        <v-divider class="mx-2" inset vertical></v-divider>
        <v-spacer></v-spacer>
      </v-toolbar>
      <v-data-table :headers="headers"
                    :items="ranking"
                    :sort-by="['num']"
                    class="elevation-1">
        <template v-slot:item.rank="{ item }">
          <v-chip>
            {{ranking.map(function(x) {return x.num; }).indexOf(item.num) + 1}}
          </v-chip>
        </template>
        <template v-slot:no-data> </template>
      </v-data-table>
      </v-flex>
    </v-layout>
  </v-container>
</template>

<script lang="ts">
import { Component, Vue } from 'vue-property-decorator';
// eslint-disable-next-line import/named
import { RankingApi, RankingDto } from '@/generated';
import { Constant } from '@/class/Constant';
import Ranking from '@/class/Ranking';

import customConfiguration = Constant.customConfiguration;

@Component
export default class Home extends Vue {
  ranking : Array<Ranking> = [];

   rankingApi = new RankingApi(customConfiguration);

    headers: any = [
      { text: 'ランキング', value: 'rank' },
      { text: '読書', value: 'name' },
      { text: '冊数', value: 'num' },
    ];

    getRanking() : void{
      this.rankingApi.rankingBooksGet().then((res) => {
        const rankingsDto = res.data.rankings;

        // eslint-disable-next-line no-restricted-syntax
        for (const rankingDto of rankingsDto) {
          const ranking = new Ranking(rankingDto.name, rankingDto.num);
          this.ranking.push(ranking);
        }
      });
    }

    mounted() : void{
      this.getRanking();
    }
}
</script>
