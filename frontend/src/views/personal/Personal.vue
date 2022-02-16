<template>
  <div>
    <div class="title"><h1>오늘의 목표</h1></div>
    <v-container>
      <v-row justify="center">
        <!-- #1. addBox -->
        <v-col cols="5" class="addBox">
          <div class="title"><h3>운동 추가하기</h3></div>
          <v-row class="d-flex" align="center">
            <div
              id="select-box"
              class="d-flex mt-3"
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
                    <img :src="`${data.item.exImg}`">
                  </v-list-item-avatar>
                  <v-list-item-content>
                    <v-list-item-title v-html="data.item.name"></v-list-item-title>
                    <v-list-item-title v-html="data.item.nums"></v-list-item-title>
                  </v-list-item-content>
                </template>
              </v-select>
              <v-select
                v-model="selectedItem.selectedSet"
                :items="items.sets"
                label="세트"
                dense
                outlined
              ></v-select>
              <v-btn
                class="mr-1 mt-1"
                fab
                dark
                x-small
                color="green"
                @click="addToList"
              >
                <v-icon>mdi-plus</v-icon>
              </v-btn>
            </div>
          </v-row>
        </v-col>
        <!-- spacer -->
        <v-col cols="1"></v-col>
        <!-- #2. listBox -->
        <v-col cols="5" class="listBox">
          <div class="title"><h3>운동 리스트</h3></div>
          <ExTodoItem
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
    <AlertNotSelect :showDialog="showNotSelect"/>
    </v-container>
  </div>
</template>

<script>
import ex from '../../../public/ex';
import ExTodoItem from './components/ExTodoItem.vue';
import AlertNotSelect from './components/AlertNotSelect.vue';

export default {
  name: 'Personal',

  components: {
    ExTodoItem,
    AlertNotSelect,
  },

  data() {
    return {
      showNotSelect: false,
      items: {
        exercises: [
          { header: '테스트' },
          { name: '테스트', nums: '2회', exImg: require('@/assets/exercise/test.png') },
          { name: '테스트(좌우)', nums: '2회', exImg: require('@/assets/exercise/test2.png') },
          { header: '전신' },
          { name: '트리 자세', nums: '12회', exImg: require('@/assets/exercise/tree.png') },
          { name: '슈퍼맨 자세', nums: '12회', exImg: require('@/assets/exercise/superman.png') },
          { name: '플랭크', nums: '30초', exImg: require('@/assets/exercise/flank.png') },
          { divider: true },
          { header: '상체' },
          { name: '활 자세', nums: '12회', exImg: require('@/assets/exercise/bow.png') },
          { name: '보트 자세', nums: '12회', exImg: require('@/assets/exercise/boat.png') },
          { divider: true },
          { header: '하체' },
          { name: '스쿼트', nums: '12회', exImg: require('@/assets/exercise/squat.png') },
          { name: '런지', nums: '12회', exImg: require('@/assets/exercise/lunge.png') },
          { name: '브릿지', nums: '12회', exImg: require('@/assets/exercise/bridge.png') },
          { name: '레그 레이즈', nums: '12회', exImg: require('@/assets/exercise/leg.png') },
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
      if (this.selectedItem.selectedEx !== '') {
        if (this.selectedItem.selectedSet > 0) {
          const selectedResult = ex.filter((v) => v.exName === this.selectedItem.selectedEx);
          // TODO: 3 -> 12 바꾸기
          const todoNum = selectedResult[0].exName === '플랭크' ? 1 : 3;
          const todoTime = selectedResult[0].exName === '플랭크' ? 30 : 5;
          const exTodo = {
            id: new Date().getTime(),
            ex_id: selectedResult[0].id,
            isDouble: selectedResult[0].isDouble,
            todoName: selectedResult[0].exName,
            numShow: selectedResult[0].exNum,
            todoNum,
            todoTime,
            todoEng: selectedResult[0].exEng,
            todoImg: selectedResult[0].exImg,
            todoSet: this.selectedItem.selectedSet,
            todoDes: selectedResult[0].description,
          };
          this.exTodos.push(exTodo);
        }
      }
    },
    deleteTodo(exTodo) {
      this.exTodos.splice(this.exTodos.indexOf(exTodo), 1);
    },
    ready() {
      if (this.exTodos.length > 0) {
        this.$store.dispatch('exTodos', this.exTodos);
        this.$router.push({
          name: 'TodayEx',
        });
      } else {
        this.showNotSelect = true;
        setTimeout(() => {
          this.showNotSelect = false;
        }, 500);
      }
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

#select-box .v-select {
  margin: 0px 5px 0px 5px;
}

</style>
