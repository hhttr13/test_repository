<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="../include/static-head.jsp" />
</head>
<body class="hold-transition login-page">
<div class="login-box">
    <div class="login-logo">
        <a href="#">
            <b>USER</b>&nbsp LOGIN
        </a>
    </div>

    <div class="login-box-body">
        <p class="login-box-msg">로그인 페이지</p>

        <form action="#" method="post">
           <table style="cellpadding: 0; cellspacing: 0; margin: 0 auto; width: 100%">
                  <tr>
                     <td style="text-align: left">
                        <p><strong>아이디를 입력해주세요.</strong>&nbsp;&nbsp;&nbsp;<span id="idCheck"></span></p>
                     </td>
                  </tr>
                  <tr>
                     <td><input type="text" name="userId" id="signInId"
                        class="form-control tooltipstered" maxlength="14"
                        required="required" aria-required="true"
                        style="margin-bottom: 25px; width: 100%; height: 40px; border: 1px solid #d9d9de"
                        placeholder="최대 14자"></td>
                  </tr>
                  <tr>
                     <td style="text-align: left">
                        <p><strong>비밀번호를 입력해주세요.</strong>&nbsp;&nbsp;&nbsp;<span id="pwCheck"></span></p>
                     </td>
                  </tr>
                  <tr>
                     <td><input type="password" size="17" maxlength="20" id="signInPw"
                        name="userPw" class="form-control tooltipstered" 
                        maxlength="20" required="required" aria-required="true"
                        style="ime-mode: inactive; margin-bottom: 25px; height: 40px; border: 1px solid #d9d9de"
                        placeholder="최소 8자"></td>
                  </tr>
                  <tr>
                     <td class="col-xs-4">
                             <label>
                                 <input type="checkbox" name="autoLogin" checked="checked"> 자동로그인
                             </label>
                         </td>
                  </tr>
                  
                  <tr>
                     <td style="width: 100%; text-align: center; colspan: 2;"><input
                        type="button" value="로그인" class="btn form-control tooltipstered" id="signIn-btn"
                        style="background-color: #ff52a0; margin-top: 0; height: 40px; color: white; border: 0px solid #f78f24; opacity: 0.8">
                     </td>
                  </tr>
                  <tr>
                     <td
                        style="width: 100%; text-align: center; colspan: 2; margin-top: 24px; padding-top: 12px; border-top: 1px solid #ececec">

                        <a class="btn form-control tooltipstered" href="<c:url value='/user/join'/>"
                        style="cursor: pointer; margin-top: 0; height: 40px; color: white; background-color: orange; border: 0px solid #388E3C; opacity: 0.8">
                           회원가입</a>
                     </td>
                  </tr>
               </table>
        </form>

        
    </div>
    <!-- /.form-box -->
</div>
<!-- /.register-box -->

<jsp:include page="../include/plugin-js.jsp" />
<script src="<c:url value='/dist/js/custom/user-validation.js'/>"></script>

</body>
</html>