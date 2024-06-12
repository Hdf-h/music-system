<!--
 * @Description: 
 * @Author: Rabbiter
 * @Date: 2023-03-16 01:26:34
-->
<template>
  <div class="song-lyric">
    <h1 class="lyric-title">歌词</h1>
    <!-- 有歌词 -->
    <ul class="has-lyric" v-if="lyr.length" key="index">
      <li v-for="(item, index) in lyr" v-bind:key="index">
        {{ item[1] }}
      </li>
    </ul>
    <!-- 没有歌词 -->
    <div v-else class="no-lyric" key="no-lyric">
      <span>暂无歌词</span>
    </div>
  </div>
</template>
<script>
import { mixin } from "../mixins";
import { mapGetters } from "vuex";
import { songOfSongId } from "../api/index";

export default {
  name: "lyric",
  mixins: [mixin],
  data() {
    return {
      lyr: [], //当前歌曲的歌词
      songId: "", //当前歌曲id
    };
  },
  computed: {
    ...mapGetters([
      "curTime", //当前歌曲播放到的位置
      "id", //当前播放的歌曲id
      "lyric", //歌词
      "listIndex", //当前歌曲在歌单中的位置
      "listOfSongs", //当前歌单列表
    ]),
  },
  methods: {
    replaceLName(str) {
      let arr = str.split("-");
      return arr[0];
    },
    //获取名字后半部分--歌名
    replaceFName(str) {
      let arr = str.split("-");
      return arr[1];
    },
    //解析歌词
    parseLyric(text) {
      let lines = text.split("\n"); //将歌词按行分解成数组
      let pattern = /\[\d{2}:\d{2}.(\d{3}|\d{2})\]/g; //时间格式的正则表达式
      let result = []; //返回值
      //对于歌词格式不对的直接返回
      if (!/\[.+\]/.test(text)) {
        return [[0, text]];
      }
      //去掉前面格式不正确的行
      while (!pattern.test(lines[0])) {
        lines = lines.slice(1);
      }
      //遍历每一行，形成一个每行带着俩元素的数组，第一个元素是以秒为计算单位的时间，第二个元素是歌词
      for (let item of lines) {
        let time = item.match(pattern); //存前面的时间段
        let value = item.replace(pattern, ""); //存后面的歌词
        for (let item1 of time) {
          let t = item1.slice(1, -1).split(":"); //取出时间，换算成数组
          if (value != "") {
            result.push([parseInt(t[0], 10) * 60 + parseFloat(t[1]), value]);
          }
        }
      }
      //按照第一个元素--时间--排序
      result.sort(function (a, b) {
        return a[0] - b[0];
      });
      return result;
    },
    loadFromQuery() {
      // 从url读取参数
      var songId = this.$route.query.songId;
      if (songId != null && songId != "") {
        songOfSongId(songId)
          .then((res) => {
            if (res == "") {
              return;
            }

            // this.toplay(res.id, res.url, res.pic, 0, res.name, res.lyric);
            this.$store.commit("setId", res.id);
            this.$store.commit(
              "setUrl",
              this.$store.state.configure.HOST + res.url
            );
            this.$store.commit(
              "setPicUrl",
              this.$store.state.configure.HOST + res.pic
            );
            this.$store.commit("setListIndex", 0);
            this.$store.commit("setTitle", this.replaceFName(res.name));
            this.$store.commit("setArtist", this.replaceLName(res.name));
            this.$store.commit("setLyric", this.parseLyric(res.lyric));
            this.$store.commit("setIsActive", false);
            this.lyr = this.lyric;

            // this.lyr = this.parseLyric(res.lyric);
          })
          .catch((err) => {
            console.log(err);
          });
      } else {
            this.lyr = this.lyric;
      }
    },
  },
  mounted() {
    this.loadFromQuery();
  },
  watch: {
    id: function () {
      this.lyr = this.parseLyric(this.listOfSongs[this.listIndex].lyric);
    },
    curTime: function () {
      if (this.lyr.length > 0) {
        for (let i = 0; i < this.lyr.length; i++) {
          if (this.curTime >= this.lyr[i][0]) {
            for (let j = 0; j < this.lyr.length; j++) {
              document.querySelectorAll(".has-lyric li")[j].style.color =
                "#000";
              document.querySelectorAll(".has-lyric li")[j].style.fontSize =
                "15px";
            }
            if (i >= 0) {
              document.querySelectorAll(".has-lyric li")[i].style.color =
                "#95d2f6";
              document.querySelectorAll(".has-lyric li")[i].style.fontSize =
                "25px";
            }
          }
        }
      }
    },
  },
};
</script>
<style lang="scss" scoped>
@import "../assets/css/lyric.scss";
</style>