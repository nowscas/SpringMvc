<#assign
    know = Session.SPRING_SECURITY_CONTEXT??
>

<#if know>
    <#assign
        user = Session.SPRING_SECURITY_CONTEXT.authentication.principal
        name = user.getUsername()
        isAdmin = user.isAdmin()
        isModer = user.isModer()
    >
<#else>
    <#assign
        name = ""
        isAdmin = false
        isModer = false
    >
</#if>