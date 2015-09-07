<%@page pageEncoding="UTF-8" session="true"%>

<div class="container">
<div class="row">
    <legend></legend>
    <div class="pull-left ">
		<a class="marR_10" href="terms"><fmt:message key="my3o.ui.terms.title"/></a>
		<span>|</span>
		<a class="marR_10" href="instructions"><fmt:message key="my3o.ui.instructions.title"/></a>
		<span>|</span>
		<a class="marR_10" href="contacts"><fmt:message key="my3o.ui.menu.guides.contacts"/></a>
        <span>|</span>
        <a class="marR_10" href="services-amount"><fmt:message key="my3o.ui.amount.title"/></a>

		<br/>
		<span сlass="pull-right">© My3o 2014  All rights reserved </span>  
	</div>
    <div class="pull-right marL_10 width450"> 
		<img class="pull-left" width=123 height=49 src="${applicationScope.resourceServerUrl}images/qiwi_logotype.png" alt="qiwiWallet">
		<span class="pull-left width300 marL_10">С помощью <a href="https://w.qiwi.com">QIWI Wallet</a> вы можете оплатить наши услуги моментально и без комиссии. 
		<br><a href="qiwiInfo">Подробнее...</a>
		</span>
		<div class="clear"></div>
    </div>
    <div class="clear"></div>
</div>
</div>

<!-- Yandex.Metrika counter -->
<script type="text/javascript">
    (function (d, w, c) {
        (w[c] = w[c] || []).push(function() {
            try {
                w.yaCounter26996640 = new Ya.Metrika({id:26996640});
            } catch(e) { }
        });

        var n = d.getElementsByTagName("script")[0],
                s = d.createElement("script"),
                f = function () { n.parentNode.insertBefore(s, n); };
        s.type = "text/javascript";
        s.async = true;
        s.src = (d.location.protocol == "https:" ? "https:" : "http:") + "//mc.yandex.ru/metrika/watch.js";

        if (w.opera == "[object Opera]") {
            d.addEventListener("DOMContentLoaded", f, false);
        } else { f(); }
    })(document, window, "yandex_metrika_callbacks");
</script>
<noscript><div><img src="//mc.yandex.ru/watch/26996640" style="position:absolute; left:-9999px;" alt="" /></div></noscript>
<!-- /Yandex.Metrika counter -->