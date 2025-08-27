拖拽
<template>
  <div class="kefu" :style="{ left: left + 'px', top: top + 'px' }" @mousedown="handleMouseDown"/>
    <!-- <img src="../../assets/images/light.svg" alt="客服图标" /> -->
   
</template>

<script>
export default {
  data() {
    return {
      isDragging: false,
      startX: 0,
      startY: 0,
      left: 0,
      top: 0,
    };
  },
  methods: {
    // 跳转
    goTo() {
      this.$router.push(`/chat`);
    },
    handleMouseDown(event) {

      this.startX = event.clientX;
      this.startY = event.clientY;
      document.addEventListener("mousemove", this.handleMouseMove);
      document.addEventListener("mouseup", this.handleMouseUp);
    },
    handleMouseMove(event) {
      this.isDragging = true;
      if (this.isDragging) {
        const deltaX = event.clientX - this.startX;
        const deltaY = event.clientY - this.startY;
        this.left += deltaX;
        this.top += deltaY;
        this.startX = event.clientX;
        this.startY = event.clientY;
      }
    },
    handleMouseUp() {
      if (this.isDragging) {
        this.isDragging = false;
        if (this.left < window.innerWidth - this.left) {
          this.left = 0;
        } else {
          this.left = window.innerWidth - 200;
        }
      } else {
        this.goTo();
      }
      document.removeEventListener("mousemove", this.handleMouseMove);
      document.removeEventListener("mouseup", this.handleMouseUp);
    },
   
  },
};
</script>

<style scoped>
.kefu {
  position: fixed;
  width: 200px;
  height: 400px;
  border-radius: 50%;
  background-color: #409eff;
  display: flex;
  justify-content: center;
  align-items: center;
  cursor: move;
  z-index: 9999;
}

/* .kefu img {
  width: 10px;
  height: 10px;
} */

.left{
  cursor: move;
}
.el-menu {
  border-right: none !important;
  width: 100%;
}

aside {
  background-color: white;
  height: 690px;
}

.el-menu-item.is-active {
  background-color: rgb(232, 244, 255) !important;
}
</style>