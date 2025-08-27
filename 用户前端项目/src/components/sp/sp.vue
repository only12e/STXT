<template>
	<div>
		<div class="kf" @mousedown="mousedown" :style="{top: `${top}px`, left: `${left}px`}">
			<!-- 菜单栏 -->
			<div class="menu" v-if="open">
				<el-button type="primary" class="menu-button" @click="lockOpen = true">锁屏功能</el-button>
				<el-button type="warning" class="menu-button" @click="open = false">关闭</el-button>
			</div>
			<!-- 锁屏页面 -->
			<div v-if="lockOpen" class="lock"></div>
		</div>
	</div>
</template>

<script>
	export default {
		name: 'LockMenu',
		data() {
			return {
				open: false,
				lockOpen: false,
				top: window.innerHeight - 40,
				left: window.innerWidth - 40,
				startX: null,
				startY: null,
				isMove: false,
			}
		},
		mounted() {
			document.addEventListener('keydown', e => {
				if (e.key === 'Escape') {
					this.lockOpen = false
				}
			})
		},
		methods: {
			mousedown(event) {
				this.startX = event.clientX
				this.startY = event.clientY
				document.addEventListener('mousemove', this.mousemove)
				document.addEventListener('mouseup', this.mouseup)
			},
			mousemove(event) {
				this.isMove = true
				if (this.isMove) {
					let x = event.clientX - this.startX
					let y = event.clientY - this.startY
					this.left += x
					this.top += y
					this.startX = event.clientX
					this.startY = event.clientY
				}
			},
			mouseup(event) {
				if (this.isMove) {
					this.isMove = false
				} else {
					this.open = true
				}
				document.removeEventListener('mousemove', this.mousemove)
				document.removeEventListener('mouseup', this.mouseup)
			}
		}
	}
</script>

<style scoped lang="scss">
	.kf {
		position: fixed;
		width: 40px;
		height: 40px;
		background-color: #2E8BC0;
		border-radius: 50%;
		cursor: move;
		z-index: 9999;

		.lock {
			position: fixed;
			left: 0;
			top: 0;
			width: 100%;
			height: 100%;
			z-index: 9999;
			background-image: url(../../assets/images/swiper2.png);
			background-size: 100% 100%;
		}
	}

	.menu {
		display: flex;
		flex-direction: column; /* 垂直布局 */
		align-items: center; /* 垂直居中 */
		position: absolute;
		top: -50px;
		left: 40px;
		padding: 10px;
	}

	.menu-button {
		width: 100%; /* 按钮宽度相同 */
		margin: 5px 0; /* 按钮间距 */
	}
</style>
