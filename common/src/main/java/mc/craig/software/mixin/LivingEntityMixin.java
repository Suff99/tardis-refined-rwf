package mc.craig.software.mixin;

import mc.craig.software.common.entity.TardisEntity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public class LivingEntityMixin {

    @Inject(at = @At("HEAD"), cancellable = true, method = "jumpFromGround()V")
    private void jump(CallbackInfo ci) {
        LivingEntity livingEntity = (LivingEntity) (Object) this;
        if(livingEntity.hasExactlyOnePlayerPassenger() && livingEntity.getFirstPassenger() instanceof TardisEntity tardis) {
            double jumpPower = ((double) livingEntity.getJumpPower() + livingEntity.getJumpBoostPower()) * 2;
            Vec3 vec3 = livingEntity.getDeltaMovement();
            livingEntity.setDeltaMovement(vec3.x, jumpPower, vec3.z);
            ci.cancel();
        }
    }

}
