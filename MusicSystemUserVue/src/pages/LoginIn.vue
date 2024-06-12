<template>
<div class="bg">
<!--    <loginLogo/>-->
    <div class="signin">
        <div class="signUp-head">
            <span>帐号登录</span>
        </div>
        <el-form :model="loginForm" ref="loginForm" label-width="70px" class="demo-ruleForm" :rules="rules">
            <el-form-item prop="username" label="用户名">
                <el-input v-model="loginForm.username" placeholder="用户名"></el-input>
            </el-form-item>
            <el-form-item prop="password" label="密码">
                <el-input type="password" v-model="loginForm.password" placeholder="密码" @keyup.enter.native="handleLoginIn"></el-input>
            </el-form-item>

            <div class="login-btn">
                <el-button @click="goSignUp" style="font-size: 20px;"><i class="iconfont icon-r-add" style="font-size: 22px;"></i> 注册</el-button>
                <el-button type="primary" @click="handleLoginIn" style="font-size: 20px;"><i class="iconfont icon-r-yes" style="font-size: 22px;"></i> 登录</el-button>
            </div>
        </el-form>
    </div>
</div>
</template>
<script>
import loginLogo from '../components/LoginLogo'
import {mixin} from '../mixins'
import {loginIn} from '../api/index'

export default {
    name: 'login-in',
    mixins: [mixin],
    components: {
        loginLogo
    },
    data() {
        return {
            loginForm: {
                username: '',       //用户名
                password: '',       //密码
            },
            rules: {
                username: [
                    { required: true, trigger: 'blur',message: '请输入用户名' }
                ],
                password: [
                    { required: true, trigger: 'blur',message: '请输入密码' }
                ]
            }
        }
    },
    mounted() {
        this.changeIndex('登录');
    },
    methods:{
        handleLoginIn(){
            let _this = this;
            let params = new URLSearchParams();
            params.append('username',this.loginForm.username);
            params.append('password',this.loginForm.password);
            loginIn(params)
                .then(res => {
                    if(res.code == 1){
                        _this.notify('登录成功','success');
                        _this.$store.commit('setLoginIn',true);
                        _this.$store.commit('setUserId',res.userMsg.id);
                        _this.$store.commit('setUsername',res.userMsg.username);
                        _this.$store.commit('setAvator',res.userMsg.avator);
                        setTimeout(function(){
                            _this.changeIndex('首页');
                            _this.$router.push({path: '/'});
                        },2000);
                    }else{
                        _this.notify('用户名或密码错误','error');
                    }
                })
                .catch(err => {
                    _this.notify('用户名或密码错误','error');
                })
        },
        goSignUp(){
            this.changeIndex('注册');
            this.$router.push({path: '/sign-up'});
        },
        changeIndex(value){
            this.$store.commit('setActiveName',value);
        }
    }
}
</script>

<style lang="scss" scoped>
@import "../assets/css/var.scss";
@import "../assets/css/global.scss";
.bg{
  background:url("../../src/assets/img/bg.jpg") no-repeat 5px 5px;
  width:100%;
  height:100%;
  background-size: cover;
  -webkit-background-size: cover;
  -o-background-size: cover;
  background-position: center 0;
}
.signin {
  background-color: rgba(245, 249, 250,.75);
  border-radius: 10px;
  width: 350px;
  margin: 265px auto;

  padding: 30px 30px;

  .signUp-head {
    text-align: center;
    margin-bottom: 10px;
    font-size: 20px;
    font-weight: 600;
  }

  .login-btn {
    @include layout(space-between);
    button {
      display: block;
      width: 50%;
    }
  }
}
</style>
