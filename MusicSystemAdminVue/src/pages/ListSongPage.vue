<template>
    <div class="table">
        <div class="crumbs" style="padding: 20px 0 5px 20px">
            <i class="iconfont icon-r-list" style="font-size: 24px">
                歌单歌曲管理</i
            >
        </div>
        <div class="container">
            <div class="handle-box">
                <el-input
                    v-model="select_word"
                    placeholder="请输入歌曲名"
                    class="handle-input"
                ></el-input>
                <br /><br />
                <el-button type="danger" style="font-size: 18px" @click="delAll"
                    ><i
                        class="iconfont icon-r-delete"
                        style="font-size: 22px"
                    ></i>
                    批量删除</el-button
                >
                <el-button
                    type="success"
                    style="font-size: 18px"
                    @click="centerDialogVisible = true"
                    ><i class="iconfont icon-r-add" style="font-size: 22px"></i>
                    添加歌曲</el-button
                >
            </div>
        </div>
        <el-table
            ref="multipleTable"
            border
            style="width: 100%"
            height="680px"
            :data="tableData"
            @selection-change="handleSelectionChange"
        >
            <el-table-column type="selection" width="40"></el-table-column>
            <el-table-column
                prop="name"
                label="歌手-歌名"
                align="center"
            ></el-table-column>
            <el-table-column
                label="操作"
                width="150"
                align="center"
                fixed="right"
            >
                <template slot-scope="scope">
                    <el-button
                        style="font-size: 18px"
                        type="danger"
                        @click="handleDelete(scope.row.id)"
                        ><i
                            class="iconfont icon-r-delete"
                            style="font-size: 22px"
                        ></i>
                        删除</el-button
                    >
                </template>
            </el-table-column>
        </el-table>
        <el-dialog
            title="添加歌曲"
            :visible.sync="centerDialogVisible"
            width="400px"
            center
        >
            <el-form
                :model="registerForm"
                ref="registerForm"
                label-width="80px"
                action=""
                id="tf"
            >
                <el-form-item prop="singerName" label="歌手名字" size="mini">
                    <el-input
                        v-model="registerForm.singerName"
                        placeholder="歌手名字"
                    ></el-input>
                </el-form-item>
                <el-form-item prop="songName" label="歌曲名字" size="mini">
                    <el-input
                        v-model="registerForm.songName"
                        placeholder="歌曲名字"
                    ></el-input>
                </el-form-item>
            </el-form>
            <span slot="footer">
                <el-button size="mini" @click="centerDialogVisible = false"
                    >取消</el-button
                >
                <el-button size="mini" @click="getSongId">确定</el-button>
            </span>
        </el-dialog>
        <el-dialog
            title="删除歌曲"
            :visible.sync="delVisible"
            width="300px"
            center
        >
            <div align="center">删除不可恢复，是否确定删除？</div>
            <span slot="footer">
                <el-button size="mini" @click="delVisible = false"
                    >取消</el-button
                >
                <el-button size="mini" @click="deleteRow">确定</el-button>
            </span>
        </el-dialog>
    </div>
</template>

<script>
import { mixin } from "../mixins/index";
import {
    listSongDetail,
    songOfSongId,
    songOfSongName,
    listSongAdd,
    delListSong,
} from "../api/index";

export default {
    mixins: [mixin],
    data() {
        return {
            centerDialogVisible: false, //添加弹窗是否显示
            delVisible: false, //删除弹窗是否显示
            registerForm: {
                //添加框
                singerName: "", //歌手名字
                songName: "", //歌曲名字
            },
            tableData: [],
            tempData: [],
            select_word: "",
            idx: -1, //当前选择项
            multipleSelection: [], //哪些项已经打勾
            songListId: "", //歌单id
        };
    },
    watch: {
        //搜索框里面的内容发生变化的时候，搜索结果table列表的内容跟着它的内容发生变化
        select_word: function () {
            if (this.select_word == "") {
                this.tableData = this.tempData;
            } else {
                this.tableData = [];
                for (let item of this.tempData) {
                    if (item.name.includes(this.select_word)) {
                        this.tableData.push(item);
                    }
                }
            }
        },
    },
    created() {
        this.songListId = this.$route.query.id;
        this.getData();
    },
    methods: {
        //查询所有歌手
        getData() {
            this.tempData = [];
            this.tableData = [];
            listSongDetail(this.songListId).then((res) => {
                for (let item of res) {
                    this.getSong(item.songId);
                }
            });
        },
        //根据歌曲id查询歌曲对象，放到tempData和tableData里面
        getSong(id) {
            songOfSongId(id)
                .then((res) => {
                    this.tempData.push(res);
                    this.tableData.push(res);
                })
                .catch((err) => {
                    console.log(err);
                });
        },
        //添加歌曲前的准备，获取到歌曲id
        getSongId() {
            let _this = this;
            var songOfName =
                _this.registerForm.singerName +
                "-" +
                _this.registerForm.songName;
            songOfSongName(songOfName).then((res) => {
                if (res[0] == undefined) {
                    this.notify("歌曲或者歌名不正确", "warning");
                    return;
                }
                _this.addSong(res[0].id);
            });
        },
        //添加歌曲
        addSong(songId) {
            let _this = this;
            let params = new URLSearchParams();
            params.append("songId", songId);
            params.append("songListId", this.songListId);

            listSongAdd(params)
                .then((res) => {
                    if (res.code == 1) {
                        this.getData();
                        this.notify("添加成功", "success");
                    } else {
                        this.notify("添加失败", "error");
                    }
                })
                .catch((err) => {
                    console.log(err);
                });
            _this.centerDialogVisible = false;
        },
        //删除一条歌曲
        deleteRow() {
            delListSong(this.idx, this.songListId)
                .then((res) => {
                    if (res) {
                        this.getData();
                        this.notify("删除成功", "success");
                    } else {
                        this.notify("删除失败", "error");
                    }
                })
                .catch((err) => {
                    console.log(err);
                });
            this.delVisible = false;
        },
    },
};
</script>

<style scoped>
.handle-box {
    margin-bottom: 20px;
}
.song-img {
    width: 100%;
    height: 80px;
    border-radius: 5px;
    margin-bottom: 5px;
    overflow: hidden;
}
.handle-input {
    width: 300px;
    display: inline-block;
}
.pagination {
    display: flex;
    justify-content: center;
}
.play {
    position: absolute;
    z-index: 100;
    width: 80px;
    height: 80px;
    display: flex;
    align-items: center;
    justify-content: center;
    cursor: pointer;
    top: 18px;
    left: 15px;
}

.icon {
    width: 2em;
    height: 2em;
    color: white;
    fill: currentColor;
    overflow: hidden;
}
</style>