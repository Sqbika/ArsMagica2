package am2.common.spell.component;

import java.util.EnumSet;
import java.util.Random;
import java.util.Set;

import com.google.common.collect.Sets;

import am2.ArsMagica2;
import am2.api.DamageSources;
import am2.api.affinity.Affinity;
import am2.api.spell.Operation;
import am2.api.spell.SpellComponent;
import am2.api.spell.SpellData;
import am2.api.spell.SpellModifiers;
import am2.client.particles.AMParticle;
import am2.client.particles.ParticleFadeOut;
import am2.common.defs.ItemDefs;
import am2.common.extensions.EntityExtension;
import am2.common.utils.SpellUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class ManaBlast extends SpellComponent{

	@Override
	public Object[] getRecipe(){
		return new Object[]{
				ItemDefs.manaFocus,
				new ItemStack(ItemDefs.rune, 1, EnumDyeColor.PURPLE.getDyeDamage()),
				ItemDefs.greaterFocus
		};
	}

	@Override
	public boolean applyEffectEntity(SpellData spell, World world, EntityLivingBase caster, Entity target) {
		float consumed = EntityExtension.For(caster).getCurrentMana();
		EntityExtension.For(caster).deductMana(consumed);
		double damage = spell.getModifiedValue((consumed / 50F), SpellModifiers.DAMAGE, Operation.MULTIPLY, world, caster, target);
		SpellUtils.attackTargetSpecial(spell, target, DamageSources.causeMagicDamage(caster), SpellUtils.modifyDamage(caster, (float)damage));
		return true;
	}
	
	@Override
	public EnumSet<SpellModifiers> getModifiers() {
		return EnumSet.of(SpellModifiers.DAMAGE);
	}

	
	@Override
	public float manaCost(){
		return 0;
	}
	
	@Override
	public float burnout(EntityLivingBase caster) {
		return 0;
	}
	
	@Override
	public ItemStack[] reagents(EntityLivingBase caster){
		return null;
	}

	@Override
	public void spawnParticles(World world, double x, double y, double z, EntityLivingBase caster, Entity target, Random rand, int colorModifier){
		double snapAngle = (2 * Math.PI) / (ArsMagica2.config.getGFXLevel() + 1) * 5;
		int count = 10;
		float inverted = 0.2F;
		for (int j = 0; j < count; j++) {
			float angle = MathHelper.cos((float) (j * inverted * Math.PI));
			for (int i = 0; i < (ArsMagica2.config.getGFXLevel() + 1) * 5; i++) {
				double posX = x + target.width / 2 + (MathHelper.cos((float) (snapAngle * i)) * angle);
				double posZ = z + target.width / 2 + (MathHelper.sin((float) (snapAngle * i)) * angle);
				AMParticle particle = (AMParticle) ArsMagica2.proxy.particleManager.spawn(world, "sparkle2", posX, target.posY + target.height / 2 + angle, posZ);
				if (particle != null) {
					particle.setIgnoreMaxAge(true);
					//particle.AddParticleController(new ParticleApproachEntity(particle, target, 0.15f, 0.1, 1, false));
					particle.AddParticleController(new ParticleFadeOut(particle, 2, false).setFadeSpeed(0.1f));
					particle.setRGBColorF(0.6f, 0f, 0.9f);
				}
			}
		}
	}

	@Override
	public Set<Affinity> getAffinity(){
		return Sets.newHashSet(Affinity.ARCANE);
	}

	@Override
	public float getAffinityShift(Affinity affinity){
		return 0.01f;
	}
	
	@Override
	public boolean applyEffectBlock(SpellData spell, World world, BlockPos blockPos, EnumFacing blockFace,
			double impactX, double impactY, double impactZ, EntityLivingBase caster) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public void encodeBasicData(NBTTagCompound tag, Object[] recipe) {
		// TODO Auto-generated method stub
		
	}
}
