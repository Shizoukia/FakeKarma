package catmoe.shizoukia.fakekarma.listener;

import catmoe.shizoukia.fakekarma.FakeKarma;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import net.md_5.bungee.api.connection.ProxiedPlayer;

import java.util.concurrent.TimeUnit;

public final class CacheCooldown {
    public static final Cache<ProxiedPlayer, Boolean> cache = Caffeine.newBuilder().expireAfterWrite(FakeKarma.config.getInt("cooldown"), TimeUnit.SECONDS).build();

    public static void record(final ProxiedPlayer player) {if (cache.getIfPresent(player) != null) { cache.put(player, true); } }
    public static boolean isCooldown(final ProxiedPlayer player) { return cache.getIfPresent(player) != null; }
}
