<#include "parts/security.ftl">
<#import "parts/common.ftl" as c>

<@c.page>
${message?ifExists}
<link rel="stylesheet" href="/static/pricePageStyle.css">

    <#if isAdmin>
        <div style="margin: auto; text-align: center;">
            <a class="nav-link" href="/addPricePost">Добавить жанр</a>
        </div>
    </#if>
    <div class="row border border-secondary">
        <div class="col-2">
            <div style="text-align: center;">Жанр</div>
        </div>
        <div class="col-2">
            <div style="text-align: center;">Цена</div>
        </div>
        <div class="col-7">
            <div style="text-align: center;">Пример работы</div>
        </div>
        <div class="col-1">
        </div>
    </div>
    <#list pricePosts as pricePost>
        <div class="row border border-secondary">
            <div class="col-2">
                <div style="text-align: center;">${pricePost.genre}</div>
            </div>
            <div class="col-2">
                <div style="text-align: center;">${pricePost.price}</div>
            </div>
            <div class="col-7">
                <audio controls preload="none" style="width: 100% !important; height: 54px !important">
                    <source src=/priceAudio/${pricePost.filename} type="audio/mpeg">
                </audio>
            </div>
            <div class="col-1">
                <#if isAdmin>
                    <a class="nav-link" style="color: #CD0303" href="/deletePrice/${pricePost.id}">delete</a>
                </#if>
            </div>
        </div>
    <#else>
        <div style="text-align: center; font-size: 200%">Цены пока не добавлены</div>
    </#list>


</@c.page>