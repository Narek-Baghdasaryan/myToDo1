<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 26.01.2024
  Time: 0:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>login</title>
   <style>
       *,
       *:before,
       *:after {
           box-sizing: border-box;
       }

       html {
           overflow-y: scroll;
       }


       a {
           text-decoration: none;
           color: #1ab188;
           transition: 0.5s ease;
       }
       a:hover {
           color: #179b77;
       }

       .form {
           background: rgba(19, 35, 47, 0.9);
           padding: 40px;
           max-width: 600px;
           margin: 40px auto;
           border-radius: 4px;
           box-shadow: 0 4px 10px 4px rgba(19, 35, 47, 0.3);
       }

       .tab-group {
           list-style: none;
           padding: 0;
           margin: 0 0 40px 0;
       }
       .tab-group:after {
           content: "";
           display: table;
           clear: both;
       }
       .tab-group li a {
           display: block;
           text-decoration: none;
           padding: 15px;
           background: rgba(160, 179, 176, 0.25);
           color: #a0b3b0;
           font-size: 20px;
           float: left;
           width: 50%;
           text-align: center;
           cursor: pointer;
           transition: 0.5s ease;
       }
       .tab-group li a:hover {
           background: #179b77;
           color: #ffffff;
       }
       .tab-group .active a {
           background: #1ab188;
           color: #ffffff;
       }

       .tab-content > div:last-child {
           display: none;
       }

       h1 {
           text-align: center;
           color: #ffffff;
           font-weight: 300;
           margin: 0 0 40px;
       }

       label {
           position: absolute;
           transform: translateY(6px);
           left: 13px;
           color: rgba(255, 255, 255, 0.5);
           transition: all 0.25s ease;
           -webkit-backface-visibility: hidden;
           pointer-events: none;
           font-size: 22px;
       }
       label .req {
           margin: 2px;
           color: #1ab188;
       }

       label.active {
           transform: translateY(50px);
           left: 2px;
           font-size: 14px;
       }
       label.active .req {
           opacity: 0;
       }

       label.highlight {
           color: #ffffff;
       }

       input,
       textarea {
           font-size: 22px;
           display: block;
           width: 100%;
           padding: 5px 10px;
           background: none;
           background-image: none;
           border: 1px solid #a0b3b0;
           color: #ffffff;
           border-radius: 0;
           transition: border-color 0.25s ease, box-shadow 0.25s ease;
       }
       input:focus,
       textarea:focus {
           outline: 0;
           border-color: #1ab188;
       }

       textarea {
           border: 2px solid #a0b3b0;
           resize: vertical;
       }

       .field-wrap {
           position: relative;
           margin-bottom: 40px;
       }

       .top-row:after {
           content: "";
           display: table;
           clear: both;
       }
       .top-row > div {
           float: left;
           width: 48%;
           margin-right: 4%;
       }
       .top-row > div:last-child {
           margin: 0;
       }

       .button {
           border: 0;
           outline: none;
           border-radius: 0;
           padding: 15px 0;
           font-size: 2rem;
           font-weight: 600;
           text-transform: uppercase;
           letter-spacing: 0.1em;
           background: #1ab188;
           color: #ffffff;
           transition: all 0.5s ease;
           -webkit-appearance: none;
       }
       .button:hover, .button:focus {
           background: #179b77;
       }

       .button-block {
           display: block;
           width: 100%;
       }

       .forgot {
           margin-top: -20px;
           text-align: right;
       }
   </style>
</head>
<body>

<div class="form">
    <% if (session.getAttribute("msg") != null) {%>
    <span style="color: red"><%=session.getAttribute("msg")%></span>
    <% session.removeAttribute("msg");%>
    <%}%>
    <span id="errorMessage" style="color: red"></span>
    <ul class="tab-group">
        <li class="tab active"><a href="#signup">Sign Up</a></li>
        <li class="tab"><a href="#login">Log In</a></li>
    </ul>

    <div class="tab-content">

        <div id="signup">
            <h1>Sign Up for Free</h1>

            <form action="/register" method="post">

                <div class="top-row">
                    <div class="field-wrap">
                        <label>First Name<span class="req">*</span>
                        </label>
                        <input type="text" required autocomplete="off" name="name"/>
                    </div>

                    <div class="field-wrap">
                        <label>Last Name<span class="req">*</span>
                        </label>
                        <input type="text" required autocomplete="off" name="surname"/>
                    </div>
                </div>

                <div class="field-wrap">
                    <label>
                        Email Address<span class="req">*</span>
                    </label>
                    <input type="text" required autocomplete="off" name="email"/>
                </div>

                <div class="field-wrap">
                    <label>
                        Set A Password<span class="req">*</span>
                    </label>
                    <input type="password" required autocomplete="off" name="password"/>
                </div>

                <button type="submit" class="button button-block"/>
                Get Started</button>

            </form>

        </div>

        <div id="login">
            <h1>Welcome Back!</h1>

            <form action="/login" method="post">

                <div class="field-wrap">
                    <label>
                        Email Address<span class="req">*</span>
                    </label>
                    <input type="text" required autocomplete="off" name="email" id="loginEmail"/>
                </div>

                <div class="field-wrap">
                    <label>
                        Password<span class="req">*</span>
                    </label>
                    <input type="password" required autocomplete="off" name="password" id="loginPass"/>
                </div>

                <button type="submit" id="loginBtn" class="button button-block"/>
                Log In</button>

            </form>

        </div>

    </div><!-- tab-content -->

</div> <!-- /form -->

<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
<script src="/js/main.js"></script>
<script>

    $('.form').find('input, textarea').on('keyup blur focus', function (e) {

        var $this = $(this),
            label = $this.prev('label');

        if (e.type === 'keyup') {
            if ($this.val() === '') {
                label.removeClass('active highlight');
            } else {
                label.addClass('active highlight');
            }
        } else if (e.type === 'blur') {
            if ($this.val() === '') {
                label.removeClass('active highlight');
            } else {
                label.removeClass('highlight');
            }
        } else if (e.type === 'focus') {

            if ($this.val() === '') {
                label.removeClass('highlight');
            } else if ($this.val() !== '') {
                label.addClass('highlight');
            }
        }

    });

    $('.tab a').on('click', function (e) {

        e.preventDefault();

        $(this).parent().addClass('active');
        $(this).parent().siblings().removeClass('active');

        target = $(this).attr('href');

        $('.tab-content > div').not(target).hide();

        $(target).fadeIn(600);

    });
</script>
</body>
</html>