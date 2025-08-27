<template>
    <div>
        <div v-if="open" class="move" :style="{ left: left + 'px', top: top + 'px' }" @mouseenter="mouseenter"
            @mouseleave="mouseleave">
            <slot name="title"></slot>
            <span style="position: absolute; right: 5px; top: 5px; cursor: pointer;" @click="open = false"><i
                    class="el-icon-close"></i></span>
        </div>
    </div>
</template>

<script>
export default {
    name: 'Move',
    data() {
        return {
            open: true,
            left: 0,
            top: 0,
            stepX: 1,
            stepY: 1,
            maxWith: null,
            maxHeight: null,
            timer: null,
        }
    },
    mounted() {
        this.init()
        this.startInterval()
        window.addEventListener('resize', () => {
            this.maxWith = document.documentElement.clientWidth - 400 - 5
            this.maxHeight = document.documentElement.clientHeight - 150 - 5
        })
    },
    destroyed() {
        clearInterval(this.timer)
    },
    methods: {
        mouseenter() {
            clearInterval(this.timer)
        },
        mouseleave() {
            this.startInterval()
        },
        init() {
            this.left = 0
            this.top = 0
            this.maxWith = document.documentElement.clientWidth - 400 - 5
            this.maxHeight = document.documentElement.clientHeight - 150 - 5
        },
        startInterval() {
            this.timer = setInterval(() => {
                this.move()
            }, 20)
        },
        move() {
            if (this.left < 0 || this.left >= this.maxWith) {
                this.stepX = -this.stepX
            }
            if (this.top < 0 || this.top >= this.maxHeight) {
                this.stepY = -this.stepY
            }
            this.left += this.stepX
            this.top += this.stepY
        }
    },
}

</script>

<style lang="scss" scoped>
.move {
    position: fixed;
    left: 0;
    top: 0;
    width: 400px;
    height: 150px;
    background-color: pink;
    z-index: 999;
    background-image: url(../../assets/images/swiper3.png);
    background-size: cover; /* 确保图片覆盖整个容器 */
    background-position: center; /* 图片居中显示 */
    background-repeat: no-repeat; /* 防止图片重复 */
}
</style>