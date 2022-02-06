<template>
  <div>
    <div class="title"><h1>오늘의 목표</h1></div>
    <v-container>
      <v-row justify="center">
        <!-- #1. addBox -->
        <v-col cols="5" class="addBox">
          <div class="title"><h3>운동 추가하기</h3></div>
          <v-row align="center">
            <v-col
              class="d-flex"
              cols="8"
            >
              <v-select
                v-model="selectedItem.selectedEx"
                :items="items.exercises"
                label="운동 종류"
                dense
                outlined
                item-text="name"
                item-value="name"
              >
                <template v-slot:item="data">
                  <v-list-item-avatar>
                    <img :src="require(`@/assets/exercise/exercise_${data.item.exImg}.png`)">
                  </v-list-item-avatar>
                  <v-list-item-content>
                    <v-list-item-title v-html="data.item.name"></v-list-item-title>
                    <v-list-item-title v-html="data.item.nums"></v-list-item-title>
                  </v-list-item-content>
                </template>
              </v-select>
            </v-col>
            <v-col
              class="d-flex"
              cols="3"
            >
              <v-select
                v-model="selectedItem.selectedSet"
                :items="items.sets"
                label="세트"
                dense
                outlined
              ></v-select>
            </v-col>
            <v-btn
              fab
              dark
              x-small
              color="green"
              @click="addToList"
            >
              <v-icon>mdi-plus</v-icon>
            </v-btn>
          </v-row>
        </v-col>
        <!-- spacer -->
        <v-col cols="1"></v-col>
        <!-- #2. listBox -->
        <v-col cols="5" class="listBox">
          <div class="title"><h3>운동 리스트</h3></div>
          <exTodoItem
            v-for="exTodo in exTodos"
            :key="exTodo.id"
            :exTodo="exTodo"
            @delete-todo="deleteTodo"
          />
        </v-col>
      </v-row>
      <div class="d-flex justify-center">
        <v-btn
          rounded
          color="white"
          text-center
          width="350px"
          @click="ready"
        >준비</v-btn>
      </div>
    </v-container>
  </div>
</template>

<script>
import exTodoItem from './components/exTodoItem.vue';

export default {
  name: 'Personal',

  components: {
    exTodoItem,
  },

  data() {
    return {
      items: {
        exercises: [
          { header: '전신' },
          { name: '버핏테스트', nums: '10회', exImg: 2 },
          { name: '엎드려팔다리들기', nums: '10회', exImg: 8 },
          { name: '슈퍼맨자세', nums: '10회', exImg: 4 },
          { divider: true },
          { header: '상체' },
          { name: '윗몸일으키기', nums: '10회', exImg: 6 },
          { name: '플랭크', nums: '30초', exImg: 7 },
          { divider: true },
          { header: '하체' },
          { name: '런지', nums: '10회', exImg: 1 },
          { name: '브릿지', nums: '10회', exImg: 3 },
          { name: '스쿼트', nums: '10회', exImg: 5 },
        ],
        sets: [
          1, 2, 3, 4, 5,
        ],
      },

      selectedItem: {
        selectedEx: '',
        selectedSet: 0,
      },

      exTodos: [
      ],
    };
  },

  methods: {
    addToList() {
      const ex = [
        {
          exName: '런지', exNum: '10회', imgNum: 1, des: '운동 설명',
        },
        {
          exName: '버핏테스트', exNum: '10회', imgNum: 2, des: '운동 설명',
        },
        {
          exName: '브릿지', exNum: '10회', imgNum: 3, des: '운동 설명',
        },
        {
          exName: '슈퍼맨자세', exNum: '10회', imgNum: 4, des: '운동 설명',
        },
        {
          exName: '스쿼트', exNum: '10회', imgNum: 5, des: '운동 설명',
        },
        {
          exName: '윗몸일으키기', exNum: '10회', imgNum: 6, des: '운동 설명',
        },
        {
          exName: '플랭크', exNum: '30초', imgNum: 7, des: '운동 설명',
        },
        {
          exName: '엎드려팔다리들기', exNum: '10회', imgNum: 8, des: '운동 설명',
        },
      ];
      if (this.selectedItem.selectedEx !== '') {
        if (this.selectedItem.selectedSet > 0) {
          const selectedResult = ex.filter((v) => v.exName === this.selectedItem.selectedEx);
          const exTodo = {
            id: new Date().getTime(),
            todoName: selectedResult[0].exName,
            todoNum: selectedResult[0].exNum,
            todoImgNum: selectedResult[0].imgNum,
            todoSet: this.selectedItem.selectedSet,
            todoDes: selectedResult[0].des,
          };
          this.exTodos.push(exTodo);
        }
      }
    },
    deleteTodo(exTodo) {
      this.exTodos.splice(this.exTodos.indexOf(exTodo), 1);
    },
    ready() {
      this.$router.push({
        name: 'TodayEx',
        query: { exTodos: this.exTodos },
      });
    },
  },
};
</script>

<style scoped>
.title {
  text-align: center;
  padding-bottom: 40px;
}

.listBox {
  height: 500px;
  border: 1px solid;
  background: rgba(255, 255, 255, 0.5);
  border-radius: 30px;
  margin-bottom: 40px;
  overflow-y: auto;
}

.addBox {
  height: 150px;
  border: 1px solid;
  background: rgba(255, 255, 255, 0.5);
  border-radius: 30px;
}

</style>
