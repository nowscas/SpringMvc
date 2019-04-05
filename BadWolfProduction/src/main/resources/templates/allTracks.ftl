<#include "parts/security.ftl">
<#import "parts/common.ftl" as c>

<@c.page>
<div style="text-align: center; font-size: 200%">${message?ifExists}</div>

<link rel="stylesheet" href="/static/audioPage.css">
    <#list tracks as track>
    <div class="container">
        <div class="row border border-secondary rounded">
            <div class="col audioName">
                ${track.trackSinger} - ${track.trackDescription}
            </div>
            <div class="col audioPlayer">
                <audio controls preload="none" style="width: 100% !important; height: 54px !important">
                    <source src=/audio/${track.filename} type="audio/mpeg">
                </audio>
            </div>
            <#if isAdmin>
                <div class="col audioEdit">
                    <a class="nav-link" href="/tracks/edit/${track.id}">edit</a>
                </div>
            </#if>
        </div>
    </div>
    <#else>
        <div style="text-align: center; font-size: 200%">Треки не найдены</div>
    </#list>
</@c.page>