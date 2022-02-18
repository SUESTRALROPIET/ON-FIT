<template>
  <div>
    <div class="title"><h1>오늘의 계획</h1></div>
    <v-container>
      <v-row justify="center">
        <v-col class="subTitleBox" cols="10">
          <!-- TODO: 더 적절한 멘트 찾기 -->
          <h2>오늘의 운동 : {{exTodos.length}}개</h2>
        </v-col>
      </v-row>
      <v-row justify="center">
        <v-col class="exListBox" cols="10">
          <div class="exListBoxScroll">
            <v-data-table
              text-center
              :align="center"
              :headers="headers"
              :items="exTodos"
              hide-default-footer
              class="elevation-1"
            >
              <template v-slot:[`item.todoEng`]="{ item }">
                <div class="p-2">
                  <v-img
                    :src="require(`@/assets/exercise/${item.todoEng}.png`)"
                    :alt="item.todoName"
                    width="60px"
                    height="60px">
                  </v-img>
                </div>
              </template>
            </v-data-table>
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
        {
          text: '', sortable: false, value: 'todoEng', align: 'end', width: '70px',
        },
        {
          text: '운동', sortable: false, value: 'todoName', align: 'start', width: '120px',
        },
        {
          text: '횟수', sortable: false, value: 'numShow', align: 'center', width: '70px',
        },
        {
          text: '세트', sortable: false, value: 'todoSet', align: 'center', width: '70px',
        },
        { text: '설명', sortable: false, value: 'todoDes' },
      ],
    };
  },

  methods: {
    start() {
      this.$router.push({
        name: 'PersonalTraining',
      });
    },
  },

  computed: {
    exTodos() {
      return this.$store.state.personalStore.exTodos;
    },
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
  border: 2px solid #FFD2B6;
  border-radius: 30px 30px 0 0;
  background: rgba(255, 255, 255, 0.5);
}

.exListBox {
  height: 438px;
  text-align: center;
  border: 2px solid #FFD2B6;
  border-radius: 0 0 30px 30px;
  background: rgba(255, 255, 255, 0.5);
  margin-bottom: 40px;
  overflow-y: auto;
}

.exListBoxScroll {
  overflow-y: auto;
}
</style>
