<template>
  <div class="card">
    <div class="card__content">
      <i v-if="isShowClose"
        class="el-icon-close card__close"
        @click="removeProject"></i>
      <div class="card__statistics">
        <div class="card__count">
          <i class="el-icon-view"></i>
          <span>{{project.viewCount}}</span>
        </div>
        <div class="card__count">
          <i class="el-icon-chat-line-round"></i>
          <span>{{project.commentCount}}</span>
        </div>
      </div>
      <div class="card__title">{{project.title}}</div>
    </div>
    <div class="card__cover-container">
      <img class="card__cover"
        :src="project.cover"
        alt="">
    </div>
  </div>
</template>

<script>
export default {
  props: {
    project: {
      type: Object,
      default: {
        id: '',
        viewCount: '',
        commentCount: ''
      }
    },
    isShowClose: {
      type: Boolean,
      default: false
    }
  },
  components: {},

  data() {
    return {
    }
  },

  methods: {
    removeProject() {
      this.$emit('remove', this.project.id)
      console.log('移除id', this.project.id);
    }
  }
}
</script>

<style lang='scss' scoped>
@import "@/assets/css/mixin.scss";
.card {
  width: 150px;
  height: 150px;
  position: relative;
  margin-bottom: 15px;
  border-radius: 5px;
  overflow: hidden;
  cursor: pointer;
  &__content {
    width: inherit;
    height: inherit;
    top: 0;
    left: 0;
    position: absolute;
    color: #fff;
    display: flex;
    flex-direction: column;
    justify-content: flex-end;
  }
  &__statistics {
    height: 20px;
    margin-bottom: 15px;
    display: flex;
    justify-content: space-between;
    padding: 0 15px;
  }
  &__close {
    position: absolute;
    top: 5px;
    right: 5px;
  }
  &__title {
    height: 20px;
    background: rgba(50, 50, 50, 0.8);
    box-shadow: 0px -1px 5px #ddd;
    padding: 2px 10px;
    line-height: 20px;
    @include overflow;
  }
  &__cover {
    &-container {
      width: inherit;
      height: inherit;
      overflow: hidden;
    }
    width: inherit;
  }
}
</style>
