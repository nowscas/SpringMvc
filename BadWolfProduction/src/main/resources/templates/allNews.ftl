<#include "parts/security.ftl">
<#import "parts/common.ftl" as c>

<@c.page>
<div style="text-align: center; font-size: 200%">${message?ifExists}</div>

<link rel="stylesheet" href="/static/allNewsStyle.css">
    <#list posts as post>
    <div class="container">
        <div class="card my-3">
            <div class="card-header">
                ${post.postHeader}
            </div>
            <#if post.filename??>
                <img src="/postImg/${post.filename}" class="card-img-top">
            </#if>
            <#if post.youtubeLink??>
                <div class="card-img-top mt-2">
                    <iframe width="100%" height="315" src="https://www.youtube.com/embed/${post.youtubeLink}" frameborder="0" allow="accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>
                </div>
            </#if>
            <div class="m-2">
                ${post.postBody}
            </div>
            <div class="card-footer text-muted">
                ${post.authorName}
            </div>
            <#if isAdmin>
                <div class="card-footer text-muted">
                    <a class="nav-link" href="/editPost/${post.id}">edit</a>
                </div>
            </#if>
        </div>
    </div>
    <#else>
        <div style="text-align: center; font-size: 200%">Новостей пока нет</div>
    </#list>
</@c.page>