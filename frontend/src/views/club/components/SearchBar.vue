<template>
  <div
    id="search-drawer-ele"
    class="d-flex flex-column"
    style="height: 100%;"
  >
    <div
      style="height: 35%;"
      class="pa-8 d-flex flex-column justify-center"
    >
      <h3 class="text-center mb-5">클럽 찾기</h3>
      <v-row>
        <v-col cols="3">
          <h4>요일</h4>
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
        <v-col cols="3" class="pt-1">
          <h4>클럽장</h4>
        </v-col>
        <v-col cols="9" class="pa-0">
          <v-text-field
            label="클럽장 닉네임"
            placeholder="클럽장 닉네임"
            class="ma-0 pa-0 pr-8"
          ></v-text-field>
        </v-col>
      </v-row>
      <v-btn
        width="50%"
        rounded
        outlined
        elevation="0"
        class="ma-auto mt-5"
      >
        <span>검색</span>
      </v-btn>
    </div>
    <div
      style="height: 65%;"
    >
      <div
        v-if="0 < searchresults.length"
        id="search-drawer-results"
        class="d-flex flex-wrap justify-space-around my-5"
      >
        <SearchBarResults
          v-for="index in searchresults.length"
          :key="index"
          class="my-3"
        />
      </div>
      <div
        v-else
        class="pa-16 text-center"
      >
        <p>찾으시는 클럽이 없습니다</p>
      </div>
    </div>
  </div>
</template>

<script>
import SearchBarResults from '@/views/club/components/SearchBarResults.vue';

export default {
  name: 'SearchDrawer',
  components: {
    SearchBarResults,
  },
  data() {
    return {
      searchresults: [3, 4, 5, 6, 6],
    };
  },
};
</script>

<style scoped>
#search-drawer-ele > div {
  margin: 0.5rem;
  border-radius: 1rem;
  background-color: rgba(225, 225, 225, 0.3);
  backdrop-filter: blur(70px) brightness(105%);
  box-shadow: rgba(0, 0, 0, 0.25) 0px 3px 8px;
}
#search-drawer-ele .v-btn {
  color: black;
  background-color: white;
}
</style>
