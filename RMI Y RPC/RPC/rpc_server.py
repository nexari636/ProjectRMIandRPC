from xmlrpc.server import SimpleXMLRPCServer
from xmlrpc.server import SimpleXMLRPCRequestHandler
import json

class RequestHandler(SimpleXMLRPCRequestHandler):
   rpc_paths = ('/RPC2',)

   # Create server
with SimpleXMLRPCServer(('localhost', 9000),
                        requestHandler=RequestHandler) as server:
    server.register_introspection_functions()
    server.register_function(len)

    @server.register_function(name='json_calculate')
    def json_process(jsoncompras,jsonproductos):
        diccionario=json.loads(jsoncompras)
        diccionarioproduc=json.loads(jsonproductos)
        total=0

        for c in diccionario:
            print(c["cantidad"])
            for p in diccionarioproduc:
                if c["idproducto"]==p["idproducto"]:
                    total=total+(float(p["preciounitario"])*int(c["cantidad"]))
                    break

        print(total," total")

        return total

    print("Server run............")
    server.serve_forever()