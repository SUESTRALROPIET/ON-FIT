<template>
  <v-dialog
    width="60rem"
  >
    <v-card id="create-club-form">
      <v-card-text class="d-flex">
        <div style="border: solid; background-color: white; border-radius: 1rem;">
          <v-row class="my-3">
            <v-col cols="4">
              <img :src="imagePreviewURL" alt="club image" width="100%">
            </v-col>
            <v-col cols="7" class="align-self-end">
              <v-file-input
                v-model="imageinput"
                accept="image/*"
                label="Club Image"
                @change="onFileChange"
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
                v-model="menu1"
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
                    label="Picker in menu"
                    prepend-icon="mdi-clock-time-four-outline"
                    readonly
                    v-bind="attrs"
                    v-on="on"
                  ></v-text-field>
                </template>
                <v-time-picker
                  v-if="menu1"
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
                v-model="menu2"
                :close-on-content-click="false"
                :nudge-right="40"
                transition="scale-transition"
                offset-y
                min-width="auto"
              >
                <template v-slot:activator="{ on, attrs }">
                  <v-text-field
                    v-model="dateRangeText"
                    label="Picker without buttons"
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
          <v-card-actions>
            <v-btn
              width="30%"
              rounded
              outlined
              elevation="0"
              class="ma-auto mt-5"
            >
              <span>클럽 만들기</span>
            </v-btn>
          </v-card-actions>
        </div>
        <div>
          <div class="ma-1" style="border: solid; background-color: white;">
            <v-row class="my-3">
              <v-col cols="4">
                <img :src="imagePreviewURL" alt="club image" width="100%">
              </v-col>
            </v-row>
          </div>
          <div class="ma-1" style="border: solid; background-color: white;">
            <v-row class="my-3">
              <v-col cols="4">
                <img :src="imagePreviewURL" alt="club image" width="100%">
              </v-col>
              <v-col cols="7" class="align-self-end">
                <v-file-input
                  v-model="imageinput"
                  accept="image/*"
                  label="Club Image"
                  @change="onFileChange"
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
                  v-model="menu1"
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
                      label="Picker in menu"
                      prepend-icon="mdi-clock-time-four-outline"
                      readonly
                      v-bind="attrs"
                      v-on="on"
                    ></v-text-field>
                  </template>
                  <v-time-picker
                    v-if="menu1"
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
                  v-model="menu2"
                  :close-on-content-click="false"
                  :nudge-right="40"
                  transition="scale-transition"
                  offset-y
                  min-width="auto"
                >
                  <template v-slot:activator="{ on, attrs }">
                    <v-text-field
                      v-model="dateRangeText"
                      label="Picker without buttons"
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
          </div>
        </div>
      </v-card-text>
    </v-card>
  </v-dialog>
</template>
<script>
export default {
  name: 'ManageClubForm',
  data() {
    return {
      imagePreviewURL: null,
      time: null,
      menu1: false,
      menu2: false,
      dates: ['2019-09-10', '2019-09-20'],
    };
  },
  props: {
    value: Boolean,
  },
  computed: {
    show: {
      get() {
        return this.value;
      },
      set(value) {
        this.$emit('input', value);
      },
    },
    dateRangeText() {
      return this.dates.join(' ~ ');
    },
  },
  methods: {
    onFileChange(payload) {
      // const file = payload.target.files[0]; // use it in case of normal HTML input
      const file = payload; // in case vuetify file input
      if (file) {
        this.imagePreviewURL = URL.createObjectURL(file);
        URL.revokeObjectURL(file); // free memory
      } else {
        this.imagePreviewURL = null;
      }
    },
  },
};
</script>
<style scoped>
#create-club-form {
  background-color: transparent;
}
.v-btn {
  color: black;
  background-color: white;
}
</style>
