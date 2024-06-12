/*
 * @Description: 
 * @Author: Rabbiter
 * @Date: 2023-03-16 01:27:10
 */
import Vue from 'vue'
import Vuex from 'vuex'
Vue.use(Vuex)

const store = new Vuex.Store({
    state:{
        HOST: 'http://127.0.0.1:9171',
        isPlay: false,          //是否播放中
        url: '',                //歌曲地址
        id: ''                  //歌曲id
    },
    getters: {
        isPlay: state => state.isPlay,
        url: state => state.url,
        id: state => state.id
    },
    mutations: {
        setIsPlay: (state,isPlay) => {state.isPlay = isPlay},
        setUrl: (state,url) => {state.url = url},
        setId: (state,id) => {state.id = id}
    }
})

export default store