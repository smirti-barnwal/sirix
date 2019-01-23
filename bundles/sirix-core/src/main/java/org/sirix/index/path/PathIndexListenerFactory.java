package org.sirix.index.path;

import static com.google.common.base.Preconditions.checkNotNull;
import org.sirix.api.PageWriteTrx;
import org.sirix.index.IndexDef;
import org.sirix.index.avltree.AVLTreeWriter;
import org.sirix.index.avltree.keyvalue.NodeReferences;
import org.sirix.index.path.summary.PathSummaryReader;
import org.sirix.node.interfaces.Record;
import org.sirix.page.UnorderedKeyValuePage;

public final class PathIndexListenerFactory {
  public PathIndexListener create(final PageWriteTrx<Long, Record, UnorderedKeyValuePage> pageWriteTrx,
      final PathSummaryReader pathSummaryReader, final IndexDef indexDef) {
    final var pathSummary = checkNotNull(pathSummaryReader);
    final var paths = checkNotNull(indexDef.getPaths());
    final var avlTreeWriter =
        AVLTreeWriter.<Long, NodeReferences>getInstance(pageWriteTrx, indexDef.getType(), indexDef.getID());

    return new PathIndexListener(paths, pathSummary, avlTreeWriter);
  }
}
