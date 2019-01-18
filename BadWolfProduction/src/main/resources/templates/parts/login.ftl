<#macro login path isRegisterForm>
<form action="${path}" method="post">
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
    <#if !isRegisterForm>
        <a href="/registration">Зарегистрироваться</a>
    <#else>
        <div class="form-group row">
            <label class="col-sm-2 col-form-label">Изображение:</label>
            <div class="col-sm-6">
                <input type="file" name="file" placeholder="Изображение" />
            </div>
        </div>
    </#if>
    <button class="btn btn-primary" type="submit"><#if isRegisterForm>Зарегистрироваться<#else>Войти</#if></button>
    <input type="hidden" name="_csrf" value="${_csrf.token}" />
</form>
</#macro>

<#macro logout>
<form action="/logout" method="post">
    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
    <button class="btn btn-primary" type="submit">Выйти</button>
</form>
</#macro>
