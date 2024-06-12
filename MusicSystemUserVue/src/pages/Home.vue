<!--
 * @Description:
 * @Author: Rabbiter
 * @Date: 2023-03-16 01:26:34
-->
<template>
    <div class="home">
        <swiper />
        <div class="section" v-for="(item, index) in songsList" :key="index">
            <div class="section-title"><i :class="item.icon" style="font-size: 34px;"></i><b style="font-size: 32px;"> {{ item.name }}</b></div>
            <content-list :contentList="item.list"></content-list>
        </div>
    </div>
</template>

<script>
import Swiper from "../components/Swiper";
import contentList from "../components/ContentList";
import { getAllSinger, getAllSongList } from "../api/index";
export default {
    name: "home",
    components: {
        Swiper,
        contentList,
    },
    data() {
        return {
            songsList: [
                { name: "歌单", list: [], icon:"iconfont icon-r-list" },
                { name: "歌手", list: [], icon:"iconfont icon-r-love" },
            ],
        };
    },
    created() {
        this.getSongList();
        this.getSinger();
    },
    methods: {
        getSongList() {
            //获取前十条歌单
            getAllSongList()
                .then((res) => {
                    this.songsList[0].list = res.slice(0, 10);
                })
                .catch((e) => {
                    if (e.data == undefined) {
                        if (
                            e ==
                            "TypeError: Cannot read properties of undefined (reading 'status')"
                        ) {
                            e = "Network Error";
                        }
                        this.$message({
                            showClose: true,
                            message: e,
                            type: "error",
                            duration: 5000,
                        });
                    } else {
                        this.$message({
                            showClose: true,
                            message: e.data,
                            type: "error",
                            duration: 5000,
                        });
                    }
                });
        },
        getSinger() {
            //获取前十名歌手
            getAllSinger()
                .then((res) => {
                    this.songsList[1].list = res.slice(0, 10);
                })
                .catch((e) => {
                    if (e.data == undefined) {
                        if (
                            e ==
                            "TypeError: Cannot read properties of undefined (reading 'status')"
                        ) {
                            e = "Network Error";
                        }
                        this.$message({
                            showClose: true,
                            message: e,
                            type: "error",
                            duration: 5000,
                        });
                    } else {
                        this.$message({
                            showClose: true,
                            message: e.data,
                            type: "error",
                            duration: 5000,
                        });
                    }
                });
        },
    },
};
</script>

<style lang="scss" scoped>
@import "../assets/css/home.scss";
</style>
