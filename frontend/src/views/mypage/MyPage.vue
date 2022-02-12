<template>
  <div
    class="d-flex flex-column"
  >
    <v-row>
      <v-col cols="4" class="mr-5">
        <div class="d-flex">
          <v-avatar
            class="mr-5 align-self-center"
            color="orange"
            size="60"
          >
            <span class="white--text text-h5">{{user_info.full_name[0]}}</span>
          </v-avatar>
          <div class="d-flex flex-column">
            <v-row>
              <h1>{{user_info.full_name}} 님</h1>
            </v-row>
            <v-row class="d-flex">
              <v-col cols="3" class="mx-2">
                <ButtonClubs/>
              </v-col>
              <v-col cols="3" class="mx-2">
                <ButtonNickname
                  :user_info="user_info"
                />
              </v-col>
              <v-col cols="3" class="mx-2">
                <ButtonDeleteUser/>
              </v-col>
            </v-row>
          </div>
        </div>
        <div id="mypage-record"
          class="d-flex flex-column my-5"
        >
          <div class="d-flex justify-space-between">
            <h4>운동시간</h4>
            <h2>00 h 00 m</h2>
          </div>
          <div class="d-flex justify-space-between">
            <h4>칼로리</h4>
            <h2>0,000 kcal</h2>
          </div>
        </div>
      </v-col>
      <v-col cols="7">
        <ExRecord/>
      </v-col>
    </v-row>
    <div id="mypage-bottom">
      <Calendar/>
    </div>
  </div>
</template>

<script>
import ButtonClubs from '@/views/mypage/components/ButtonClubs.vue';
import ButtonNickname from '@/views/mypage/components/ButtonNickname.vue';
import ButtonDeleteUser from '@/views/mypage/components/ButtonDeleteUser.vue';
import ExRecord from '@/views/mypage/components/ExRecord.vue';
import Calendar from '@/views/mypage/components/Calendar.vue';

export default {
  name: 'MyPage',
  components: {
    ButtonClubs,
    ButtonNickname,
    ButtonDeleteUser,
    ExRecord,
    Calendar,
  },
  data() {
    return {
      user_info: {
        user_email: 'abc@ssafy.com',
        full_name: '김아무개',
        created_at: '1010-10-10',
        trainer_code: 1,
        monthlyCal: 100,
        monthlyTime: 100,
      },
    };
  },
};
</script>

<style scoped>
#mypage-record div{
  padding: 1rem 0rem;
}
</style>
