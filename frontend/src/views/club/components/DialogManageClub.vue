<template>
  <v-card id="manage-club-form">
    <v-row class="d-flex">
      <v-col cols="6">
        <v-card-text>
          <v-row class="my-3">
            <v-col cols="4">
              <img :src="imagePreviewURL" alt="club image" width="100%">
            </v-col>
            <v-col cols="7" class="align-self-end">
              <v-file-input
                accept="image/*"
                label="Club Image"
                @change="onImageFileChange"
              ></v-file-input>
              <v-text-field
                label="클럽명*"
                required
              ></v-text-field>
            </v-col>
          </v-row>
          <v-row>
            <v-col cols="3" class="text-center">
              <h3>요일</h3>
            </v-col>
            <v-col cols="9" class="pl-0">
              <div class="d-flex flex-wrap">
                <v-checkbox
                  dense
                  label="월"
                  color="success"
                  class="ma-0 pa-0 mr-3"
                ></v-checkbox>
                <v-checkbox
                  dense
                  label="화"
                  color="success"
                  class="ma-0 pa-0 mr-3"
                ></v-checkbox>
                <v-checkbox
                  dense
                  label="수"
                  color="success"
                  class="ma-0 pa-0 mr-3"
                ></v-checkbox>
                <v-checkbox
                  dense
                  label="목"
                  color="success"
                  class="ma-0 pa-0 mr-3"
                ></v-checkbox>
                <v-checkbox
                  dense
                  label="금"
                  color="success"
                  class="ma-0 pa-0 mr-3"
                ></v-checkbox>
                <v-checkbox
                  dense
                  label="토"
                  color="success"
                  class="ma-0 pa-0 mr-3"
                ></v-checkbox>
                <v-checkbox
                  dense
                  label="일"
                  color="success"
                  class="ma-0 pa-0 mr-3"
                ></v-checkbox>
              </div>
            </v-col>
          </v-row>
          <v-row>
            <v-col cols="3"  class="text-center align-self-center">
              <h3>시간</h3>
            </v-col>
            <v-col cols="8" class="pl-0">
              <v-menu
                ref="menu"
                v-model="timePicker"
                :close-on-content-click="false"
                :nudge-right="40"
                :return-value.sync="time"
                transition="scale-transition"
                offset-y
                max-width="290px"
                min-width="290px"
              >
                <template v-slot:activator="{ on, attrs }">
                  <v-text-field
                    v-model="time"
                    label="Pick Time"
                    prepend-icon="mdi-clock-time-four-outline"
                    readonly
                    v-bind="attrs"
                    v-on="on"
                  ></v-text-field>
                </template>
                <v-time-picker
                  v-if="timePicker"
                  v-model="time"
                  full-width
                  @click:minute="$refs.menu.save(time)"
                ></v-time-picker>
              </v-menu>
            </v-col>
          </v-row>
          <v-row>
            <v-col cols="3"  class="text-center align-self-center">
              <h3>운동기간</h3>
            </v-col>
            <v-col cols="8" class="pl-0">
              <v-menu
                v-model="datePicker"
                :close-on-content-click="false"
                :nudge-right="40"
                transition="scale-transition"
                offset-y
                min-width="auto"
              >
                <template v-slot:activator="{ on, attrs }">
                  <v-text-field
                    v-model="dateRangeText"
                    label="Pick Date"
                    prepend-icon="mdi-calendar"
                    readonly
                    v-bind="attrs"
                    v-on="on"
                  ></v-text-field>
                </template>
                <v-date-picker
                  v-model="dates"
                  v-show="dates.length <= 2"
                  range
                ></v-date-picker>
              </v-menu>
            </v-col>
          </v-row>
        </v-card-text>
      </v-col>
      <v-divider vertical></v-divider>
      <v-col cols="6">
        <v-card-text>
          <h2 class="my-2">운동 추가하기</h2>
          <div class="mt-7 d-flex">
            <div
              id="select-box"
              class="d-flex"
            >
              <v-select
                v-model="selectedItem.selectedEx"
                :items="items.exercises"
                label="운동종류"
                outlined
                item-text="name"
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
              <v-select
                v-model="selectedItem.selectedSet"
                :items="items.sets"
                label="세트"
                outlined
              ></v-select>
            </div>
            <v-btn
              fab
              dark
              x-small
              color="green"
              @click="addToList"
              class="mt-2"
            >
              <v-icon>mdi-plus</v-icon>
            </v-btn>
          </div>
        </v-card-text>
        <v-divider></v-divider>
        <v-card-text>
          <h2 class="my-2">운동 리스트</h2>
          <div class="exTodoItems-container">
            <ExTodoItem
              v-for="exTodo in exTodos"
              :key="exTodo.id"
              :exTodo="exTodo"
              @delete-todo="deleteTodo"
            />
          </div>
        </v-card-text>
      </v-col>
    </v-row>
    <v-card-actions>
      <v-btn
        id="manage-club-button"
        width="30%"
        rounded
        outlined
        elevation="0"
        class="ma-auto mt-5"
      >
        <span>수정하기</span>
      </v-btn>
    </v-card-actions>
  </v-card>
</template>
<script>
import ExTodoItem from '@/views/club/components/ExTodoItem.vue';

export default {
  name: 'DialogManageClub',
  components: {
    ExTodoItem,
  },
  props: {
    value: Boolean,
    ClubInfo: Object,
  },
  data() {
    return {
      timePicker: false,
      datePicker: false,
      imagePreviewURL: require('@/assets/club/club_default.png'),
      time: null,
      dates: [],

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
      exTodos: [],
    };
  },
  computed: {
    show: {
      get() {
        return this.value;
      },
    },
    dateRangeText() {
      return this.dates.join(' ~ ');
    },
  },
  methods: {
    onImageFileChange(payload) {
      const defaultImage = require('@/assets/club/club_default.png');
      const file = payload;
      if (file) {
        this.imagePreviewURL = URL.createObjectURL(file);
        URL.revokeObjectURL(file);
      } else {
        this.imagePreviewURL = defaultImage;
      }
    },
    addToList() {
      const ex = [
        {
          exName: '런지', exNum: '10회', imgNum: 1,
        },
        {
          exName: '버핏테스트', exNum: '10회', imgNum: 2,
        },
        {
          exName: '브릿지', exNum: '10회', imgNum: 3,
        },
        {
          exName: '슈퍼맨자세', exNum: '10회', imgNum: 4,
        },
        {
          exName: '스쿼트', exNum: '10회', imgNum: 5,
        },
        {
          exName: '윗몸일으키기', exNum: '10회', imgNum: 6,
        },
        {
          exName: '플랭크', exNum: '30초', imgNum: 7,
        },
        {
          exName: '엎드려팔다리들기', exNum: '10회', imgNum: 8,
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
          };
          this.exTodos.push(exTodo);
        }
      }
    },
    deleteTodo(exTodo) {
      this.exTodos.splice(this.exTodos.indexOf(exTodo), 1);
    },
  },
};
</script>
<style scoped>
#manage-club-form {
  padding: 2rem;
  border-radius: 1rem;
}
#manage-club-button {
  color: black;
  background-color: white;
}
#select-box .v-select {
  margin: 0px 5px 0px 5px;
}
.exTodoItems-container {
  max-height: 18rem;
  display: flex;
  flex-direction: column;
  overflow-y: scroll;
}
</style>
