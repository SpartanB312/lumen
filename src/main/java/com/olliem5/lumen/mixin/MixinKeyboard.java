package com.olliem5.lumen.mixin;

import com.olliem5.lumen.Lumen;
import com.olliem5.lumen.impl.events.KeyPressEvent;
import net.minecraft.client.Keyboard;
import org.lwjgl.glfw.GLFW;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * @author olliem5
 * @since 1.0
 */

@Mixin(value = Keyboard.class)
public final class MixinKeyboard {
    @Inject(method = "onKey", at = @At(value = "HEAD"))
    private void onKey(long window, int key, int scancode, int i, int j, CallbackInfo callbackInfo) {
        if (key != GLFW.GLFW_KEY_UNKNOWN) {
            if (i != GLFW.GLFW_RELEASE) {
                KeyPressEvent keyPressEvent = new KeyPressEvent(key);
                Lumen.EVENT_HANDLER.dispatchPaceEvent(keyPressEvent);
            }
        }
    }
}
