package org.krjura.devtools.filters

import org.krjura.devtools.utils.ServerTimingBuilder
import org.springframework.stereotype.Component
import org.springframework.web.server.ServerWebExchange
import org.springframework.web.server.WebFilter
import org.springframework.web.server.WebFilterChain
import reactor.core.publisher.Mono

@Component
class LatencyFilter : WebFilter {

    override fun filter(exchange: ServerWebExchange, chain: WebFilterChain): Mono<Void> {
        val latencyStart = System.currentTimeMillis();

        exchange.response.beforeCommit {
            val duration = System.currentTimeMillis() - latencyStart;

            exchange.response.headers.addAll(ServerTimingBuilder().addApp(duration).build())

            Mono.empty();
        }

       return chain.filter(exchange);
    }
}