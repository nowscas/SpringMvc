<#import "parts/common.ftl" as c>

<@c.page>
    <#list tracks as track>
        <div>${track.trackSinger}- ${track.trackDescription}</div>
        <audio controls preload="none">
            <source src=/audio/${track.filename} type="audio/mpeg">
        </audio>
    <#else>
        Треки не найдены
    </#list>
</@c.page>