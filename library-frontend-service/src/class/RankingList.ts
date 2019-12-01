import Ranking from '@/class/Ranking';

export default class RankingList {
  rankings: Array<Ranking>;

  constructor(rankings: Array<Ranking>) {
    this.rankings = rankings;
  }
}
