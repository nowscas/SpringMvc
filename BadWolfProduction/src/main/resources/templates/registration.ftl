<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>

<@c.page>
Регистрация нового пользователя
${message}
<@l.login "/registration" />
</@c.page>

