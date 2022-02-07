<template>
  <div>
    <div class="title"><h1>운동 페이지</h1></div>
    <v-container>
      <v-row justify="center">
        <!-- 1. webRTC 화면 -->
        <v-col class="canvas" cols="5">
          <p>web 화면</p>
        </v-col>
        <!-- spacer -->
        <v-col cols="1"></v-col>
        <!-- 2. 운동 status -->
        <v-col class="statusBox" cols="5">
          <div class="numBox mt-4">
            <h1 class="text-center">00</h1>
          </div>
          <h1 class="mt-4 ps-10">타이머</h1>
          <h1 class="mt-4 ps-10">칼로리</h1>
          <h1 class="mt-4 ps-10">세트</h1>
        </v-col>

        <!-- 3. 버튼 -->
        <v-col class="btnBox d-flex justify-center pt-0" cols="12">
          <v-btn
            class="mr-6"
            fab
            dark
            small
          >
            <v-icon>mdi-stop</v-icon>
          </v-btn>
          <v-btn
            fab
            dark
            small
          >
            <v-icon>mdi-logout</v-icon>
          </v-btn>
        </v-col>
      </v-row>
    </v-container>
  </div>
</template>

<script>
export default {
  name: 'PersonalTraining',
  components: {

  },
  data() {
    return {
      exTodos: [],
    };
  },
  created() {
    this.exTodos = this.$route.query.exTodos;
    console.log(this.exTodos);
  },
};
</script>

<style scoped>
.title {
  text-align: center;
  padding-bottom: 40px;
}

.canvas {
  height: 500px;
  border: 1px solid;
  background: rgba(255, 255, 255, 0.5);
  border-radius: 30px;
  margin-bottom: 40px;
}

.statusBox {
  height: 500px;
  border: 1px solid;
  background: rgba(255, 255, 255, 0.5);
  border-radius: 30px;
  margin-bottom: 40px;
}

.numBox {
  width: 235px;
  height: 235px;
  background: #FEE8D6;
  border-radius: 50%;
}
</style>
