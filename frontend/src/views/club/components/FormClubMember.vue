<template>
  <div class="d-flex justify-space-between">
    <div>
      <p>{{exTodo.todoName}}</p>
    </div>
    <div>
      <v-btn
        dark
        x-small
        @click="deleteTodo"
      >
        내보내기
      </v-btn>
    </div>
  </div>
</template>

<script>
export default {
  name: 'FormClubMember',
  props: {
    exTodo: {
      type: Object,
      required: true,
    },
  },
  methods: {
    deleteTodo() {
      this.$emit('delete-todo', this.exTodo);
    },
  },
};
</script>

<style scoped>
.exBox {
  margin-left: 10px;
  height: 77px;
  /* border: 1px solid; */
  background: #ffd2b625;
  border-radius: 30px 0 0 30px;
  padding: 0;
  margin-bottom: 30px;
}
.setBox {
  height: 77px;
  /* border: 1px solid; */
  background: #FF8F4A25;
  border-radius: 0 30px 30px 0;
  padding: 0;
  margin-bottom: 30px;
}
</style>
