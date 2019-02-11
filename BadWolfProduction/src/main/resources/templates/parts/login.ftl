<#macro login path isRegisterForm>
<link rel="stylesheet" href="/static/loginStyle.css">
<form action="${path}" method="post" enctype="multipart/form-data">
        <div class="form-group row">
        <label class="col-sm-2 col-form-label">Имя пользователя:</label>
        <div class="col-sm-6">
            <input type="text" name="username" class="form-control" placeholder="Имя пользователя" />
        </div>
    </div>
    <div class="form-group row">
        <label class="col-sm-2 col-form-label">Пароль:</label>
        <div class="col-sm-6">
            <input type="password" name="password" class="form-control" placeholder="Пароль" />
        </div>
    </div>
    <#if isRegisterForm>
        <div class="form-group row">
            <label class="col-sm-2 col-form-label">Изображение:</label>
            <div class="col-sm-6">
                <input type="file" name="file" placeholder="Изображение" />
            </div>
        </div>
    </#if>
    <div class="enterbtn">
        <button class="btn btn-light" type="submit"><#if isRegisterForm>Зарегистрироваться<#else>Войти</#if></button>
    </div>
    <#if !isRegisterForm>
        <div class="regbnt">
            <a href="/registration" style="color:white">Регистрация</a>
        </div>
    </#if>
    <input type="hidden" name="_csrf" value="${_csrf.token}" />
</form>
</#macro>

<#macro logout>
<form action="/logout" method="post">
    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
    <button class="btn btn-light" type="submit">Выйти</button>
</form>
</#macro>

<#macro navbarLogin>
<form action="/login" method="post">
    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
    <button class="btn btn-light" type="submit">Войти</button>
</form>
</#macro>
