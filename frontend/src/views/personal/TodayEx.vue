<template>
  <div>
    <div class="title"><h1>오늘의 계획</h1></div>
    <v-container>
      <v-row justify="center">
        <v-col class="subTitleBox" cols="8">
          <h2>운동 소개 페이지</h2>
        </v-col>
      </v-row>
      <v-row justify="center">
        <v-col class="exListBox" cols="8">
          <div class="exListBoxScroll">
            <v-data-table
              :headers="headers"
              :items="exTodos"
              hide-default-header
              hide-default-footer
              class="elevation-1"
            ></v-data-table>
          </div>
        </v-col>
      </v-row>
      <div class="d-flex justify-center">
        <v-btn
          rounded
          color="white"
          text-center
          width="350px"
          @click="start"
        >시작</v-btn>
      </div>
    </v-container>
  </div>
</template>

<script>
export default {
  name: 'TodayEx',
  components: {

  },
  data() {
    return {
      headers: [
        // TODO: value에 img 넣기
        { text: 'Image', sortable: false, value: 'todoImgNum' },
        { text: '운동', sortable: false, value: 'todoName' },
        { text: '횟수', sortable: false, value: 'todoNum' },
        { text: '세트', sortable: false, value: 'todoSet' },
        { text: '설명', sortable: false, value: 'todoDes' },
      ],
      exTodos: [],
    };
  },

  methods: {
    start() {
      this.$router.push({
        name: 'PersonalTraining',
        query: { exTodos: this.exTodos },
      });
    },
  },

  created() {
    this.exTodos = this.$route.query.exTodos;
  },
};
</script>

<style scoped>
.title {
  text-align: center;
  padding-bottom: 40px;
}

.subTitleBox {
  text-align: center;
  border: 1px solid;
  border-radius: 30px 30px 0 0;
  background: rgba(255, 255, 255, 0.5);
}

.exListBox {
  height: 438px;
  text-align: center;
  border: 1px solid;
  border-radius: 0 0 30px 30px;
  background: rgba(255, 255, 255, 0.5);
  margin-bottom: 40px;
  overflow-y: auto;
}

.exListBoxScroll {
  overflow-y: auto;
}
</style>
