
<template>
  <div>
    <div>
      <h2 style="text-align: center;">取色板</h2>
      <ul
        style="list-style: none; padding: 0; margin: 0; display: flex; flex-direction: column; height: 500px;">
        <li draggable="true" style="background-color: red; flex: 1; margin: 5px 10px;"></li>
        <li draggable="true" style="background-color: orange; flex: 1; margin: 5px 10px;"></li>
        <li draggable="true" style="background-color: yellow; flex: 1; margin: 5px 10px;"></li>
        <li draggable="true" style="background-color: green; flex: 1; margin: 5px 10px;"></li>
        <li draggable="true" style="background-color: greenyellow; flex: 1; margin: 5px 10px;"></li>
        <li draggable="true" style="background-color: blue; flex: 1; margin: 5px 10px;"></li>
        <li draggable="true" style="background-color: purple; flex: 1; margin: 5px 10px;"></li>
      </ul>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      color: null,
    };
  },
  mounted() {
    document.addEventListener('dragstart', this.handleDragStart);
    document.addEventListener('drop', this.handleDrop);
    document.addEventListener('dragover', this.handleDragOver);
  },
  beforeDestroy() {
    document.removeEventListener('dragstart', this.handleDragStart);
    document.removeEventListener('drop', this.handleDrop);
    document.removeEventListener('dragover', this.handleDragOver);
  },
  methods: {
    handleDragStart(e) {
      if (e.target.localName == 'li') {
        this.color = e.target.style.backgroundColor;
      }
    },
    handleDrop(e) {
      let parentNode = this.getParentNode(e.target);
      if (parentNode) {
        parentNode.style.backgroundColor = this.color;
        parentNode.style.color = '#fff';
      }
    },
    handleDragOver(e) {
      e.preventDefault();
    },
    getParentNode(node) {
      if (!node) {
        return null; // 如果节点为空，则返回 null
      }
      // 如果节点本身是<tr>元素，则直接返回
      if (node.localName === 'tr') {
        return node;
      }
      // 否则，递归查找父节点
      return this.getParentNode(node.parentNode);
    },
  },
};
</script>

<style scoped>
</style>





