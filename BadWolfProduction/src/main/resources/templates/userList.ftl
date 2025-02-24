<#import "parts/common.ftl" as c>

<@c.page>
Список пользователей
<table>
    <thead>
    <tr>
        <th>Name</th>
        <th>Role</th>
        <th>Image</th>
    </tr>
    </thead>
    <tbody>
    <#list users as user>
    <tr>
        <td>${user.username}</td>
        <td><#list user.roles as role>${role}<#sep>, </#list></td>
        <td>
            <#if user.filename??>
                <img src="/img/${user.filename}">
            </#if>
        </td>
        <td><a href="/user/${user.id}">edit</a></td>
    </tr>
    </#list>
    </tbody>
</table>
</@c.page>