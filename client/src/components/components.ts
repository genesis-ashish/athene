import { EntityManagement } from '@genesislcap/foundation-entity-management';
import { FASTRouter } from '@microsoft/fast-router';
import { zeroGridComponents } from '@genesislcap/foundation-zero-grid-pro';
import { g2plotChartsComponents } from '@genesislcap/g2plot-chart';
import { logger } from '../utils';
import { Form } from '@genesislcap/foundation-forms';
import {FoundationLayout, foundationLayoutComponents} from '@genesislcap/foundation-layout';
import {foundationDesignSystemProvider, registerFoundationDesignSystem} from '@genesislcap/foundation-ui'

EntityManagement;
Form;
FoundationLayout;

enum ResourceType {
  LOCAL = 'LOCAL',
  REMOTE = 'REMOTE',
}

/**
 * TODO: Think about sharing import functions across micro frontends.
 */
function loadZeroFallback() {
  return import(
    /* webpackMode: "lazy" */
    '@genesislcap/foundation-zero'
  );
}

function loadFoundationLayoutFallback() {
  return import(
    /* webpackMode: "lazy" */
    '@genesislcap/foundation-layout'
  );
}

async function loadFuiFallback() {
  return import(
    /* webpackMode: "lazy" */
    '@genesislcap/foundation-ui'
  );
}


/**
 * Granular
 */
async function loadZeroDesignSystem() {
  let type = ResourceType.REMOTE;
  try {
    // @ts-ignore
    return await import('foundationZero/ZeroDesignSystem');
  } catch (e) {
    type = ResourceType.LOCAL;
    return await loadZeroFallback();
  } finally {
    logger.debug(`Using '${type}' version of foundationZero/ZeroDesignSystem`);
  }
}

export type LoadRemotesOptions = {};

export async function loadRemotes() {
  const { provideDesignSystem, baseComponents } = await loadZeroDesignSystem();
  const { registerFoundationDesignSystem } = await loadFuiFallback();
  const { foundationLayoutComponents} = await loadFoundationLayoutFallback();
  return {
    FoundationDesignSystem: registerFoundationDesignSystem().register(foundationLayoutComponents),
    ZeroDesignSystem: provideDesignSystem().register(
      baseComponents,
      zeroGridComponents,
      g2plotChartsComponents,
      
    ),
  };
}

FASTRouter;
