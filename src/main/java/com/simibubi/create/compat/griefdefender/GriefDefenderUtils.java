package com.simibubi.create.compat.griefdefender;

import com.griefdefender.api.GriefDefender;

import com.griefdefender.api.claim.Claim;
import com.griefdefender.api.claim.TrustTypes;
import com.griefdefender.api.data.ClaimData;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import org.spongepowered.api.Sponge;
import org.spongepowered.api.world.Location;

import java.util.Objects;
import java.util.UUID;

public class GriefDefenderUtils {

	public static boolean isEnabled(){
		return true; //TODO: GriefDefender availability check
	}

	public static boolean canInteract(World world, BlockPos pos1, BlockPos pos2){
		if(!isEnabled())return true;
		return Objects.equals(getRegionUUID(world, pos1), getRegionUUID(world, pos2));
	}
	public static UUID getRegionUUID(World world, BlockPos pos){
		UUID worldID=Sponge.server().worldManager().defaultWorld().uniqueId();
		return GriefDefender.getCore().getClaimAt(worldID,pos.getX(),pos.getY(),pos.getZ()).getUniqueId();
	}

	public static boolean canUse(PlayerEntity player, BlockPos pos){
		if(!isEnabled())return true;
		UUID worldID=Sponge.server().worldManager().defaultWorld().uniqueId();
		Claim gclaim = GriefDefender.getCore().getClaimAt(worldID,pos.getX(),pos.getY(),pos.getZ());
		return gclaim.isUserTrusted(player.getUUID(), TrustTypes.BUILDER) || gclaim.isWilderness();
	}
}
