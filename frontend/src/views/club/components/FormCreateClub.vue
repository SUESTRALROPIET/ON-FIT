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
                v-model="clubName"
                label="클럽명"
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
                  v-model="checkboxMon"
                  label="월"
                  dense color="success"
                  class="ma-0 pa-0 mr-3"
                ></v-checkbox>
                <v-checkbox
                  v-model="checkboxTues"
                  label="화"
                  dense color="success"
                  class="ma-0 pa-0 mr-3"
                ></v-checkbox>
                <v-checkbox
                  v-model="checkboxWedn"
                  label="수"
                  dense color="success"
                  class="ma-0 pa-0 mr-3"
                ></v-checkbox>
                <v-checkbox
                  v-model="checkboxThur"
                  label="목"
                  dense color="success"
                  class="ma-0 pa-0 mr-3"
                ></v-checkbox>
                <v-checkbox
                  v-model="checkboxFri"
                  label="금"
                  dense color="success"
                  class="ma-0 pa-0 mr-3"
                ></v-checkbox>
                <v-checkbox
                  v-model="checkboxSat"
                  label="토"
                  dense color="success"
                  class="ma-0 pa-0 mr-3"
                ></v-checkbox>
                <v-checkbox
                  v-model="checkboxSun"
                  label="일" dense
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
                  format="24hr"
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
            <FormExTodoItem
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
        width="30%" rounded outlined elevation="0"
        class="ma-auto mt-5"
        @click="createClub"
      >
        <span>클럽 만들기</span>
      </v-btn>
    </v-card-actions>
  </v-card>
</template>
<script>
import axios from 'axios';
import FormExTodoItem from '@/views/club/components/FormExTodoItem.vue';
import ex from '../../../../public/ex';

export default {
  name: 'FormCreateClub',
  components: {
    FormExTodoItem,
  },
  props: {
    value: Boolean,
  },
  data() {
    return {
      timePicker: false,
      datePicker: false,
      imagePreviewURL: require('@/assets/club/club_default.png'),
      clubName: '',
      checkboxMon: false,
      checkboxTues: false,
      checkboxWedn: false,
      checkboxThur: false,
      checkboxFri: false,
      checkboxSat: false,
      checkboxSun: false,
      time: null,
      dates: [],
      items: {
        exercises: [
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
      exTodos: [],
      exTodosforPost: [],
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
      if (this.selectedItem.selectedEx !== '') {
        if (this.selectedItem.selectedSet > 0) {
          const selectedResult = ex.filter((v) => v.exName === this.selectedItem.selectedEx);
          const exTodo1 = {
            todoName: selectedResult[0].exName,
            todoEng: selectedResult[0].exEng,
            todoNum: selectedResult[0].exNum,
            todoImg: selectedResult[0].exImg,
            todoSet: this.selectedItem.selectedSet,
          };
          const exTodo2 = {
            exerciseId: selectedResult[0].id,
            exCount: this.selectedItem.selectedSet,
          };
          this.exTodos.push(exTodo1);
          this.exTodosforPost.push(exTodo2);
        }
      }
    },
    deleteTodo(exTodo) {
      this.exTodos.splice(this.exTodos.indexOf(exTodo), 1);
      this.exTodosforPost.splice(this.exTodosforPost.indexOf(exTodo), 1);
    },
    createClub() {
      const userId = 'ssafy2';
      const newClubInfo = {
        clubId: {
          clubImg: this.imagePreviewURL,
          clubName: this.clubName,
          manager: userId,
          mon: this.checkboxMon,
          tues: this.checkboxTues,
          wedn: this.checkboxWedn,
          thur: this.checkboxThur,
          fri: this.checkboxFri,
          sat: this.checkboxSat,
          sun: this.checkboxSun,
          startDate: new Date(this.dates[0]),
          endDate: new Date(this.dates[1]),
          fixTime: this.time,
        },
        clubLogs: this.exTodosforPost,
      };
      const validateDate = this.checkboxMon || this.checkboxTues || this.checkboxWedn
        || this.checkboxThur || this.checkboxFri || this.checkboxSat || this.checkboxSun;
      if (this.clubName === '') {
        alert('클럽명을 입력해주세요');
      } else if (validateDate === false) {
        alert('클럽운동을 진행할 요일을 선택해주세요');
      } else if (this.time === null) {
        alert('클럽운동을 진행할 시간을 선택해주세요');
      } else if (this.dates.length < 2) {
        alert('클럽운동을 진행할 운동기간을 선택해주세요');
      } else if (this.exTodosforPost.length < 1) {
        alert('클럽운동에서 진행할 운동을 선택해주세요');
      } else {
        axios.post(`http://localhost:8081/club/${userId}`, newClubInfo, {
          headers: {
            'Access-Control-Allow-Origin': '*',
            'Content-type': 'application/json',
          },
        })
          .then((response) => {
            console.log(response);
          })
          .catch((err) => {
            console.log(err);
          });
        this.clearAll();
      }
    },
    clearAll() {
      this.imagePreviewURL = require('@/assets/club/club_default.png');
      this.clubName = '';
      this.checkboxMon = false;
      this.checkboxTues = false;
      this.checkboxWedn = false;
      this.checkboxThur = false;
      this.checkboxFri = false;
      this.checkboxSat = false;
      this.checkboxSun = false;
      this.time = null;
      this.dates = [];
      this.selectedItem.selectedEx = '';
      this.selectedItem.selectedSet = 0;
      this.exTodos = [];
      this.exTodosforPost = [];
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
.club-members-container {
  max-height: 5.5rem;
  display: flex;
  flex-direction: column;
  overflow-y: scroll;
}
.exTodoItems-container {
  max-height: 23rem;
  display: flex;
  flex-direction: column;
  overflow-y: scroll;
}
</style>
