package de.mardybum.api;

import de.mardybum.blockchain.Blockchain;

import javax.inject.Singleton;

@Singleton
public class BCInstance {
    Blockchain bc = new Blockchain();
}
